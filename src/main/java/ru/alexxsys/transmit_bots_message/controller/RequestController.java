package ru.alexxsys.transmit_bots_message.controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.alexxsys.transmit_bots_message.configuration.Config;
import ru.alexxsys.transmit_bots_message.entity.History;
import ru.alexxsys.transmit_bots_message.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexxsys.transmit_bots_message.repository.HistoryRepository;
import ru.alexxsys.transmit_bots_message.repository.RequestRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RequestController {
    private final RequestRepository repositoryRequest;
    private final HistoryRepository repositoryHistory;
    private final Config config;
    private static final int sizeIO = 1024;

    @Autowired
    private RequestController(RequestRepository repositoryRequest, HistoryRepository repositoryHistory, Config config) {
        this.repositoryRequest = repositoryRequest;
        this.repositoryHistory = repositoryHistory;
        this.config = config;
    }

    @RequestMapping(path = "/botsystem/**", method = RequestMethod.POST)
    public void putRequest(HttpServletRequest HttpRequest,
                           HttpServletResponse HttpResponse,
                           @RequestHeader  Map<String, String> headers,
                           @RequestParam Map<String, String> parameters) throws IOException {

        UUID uuid = UUID.randomUUID();

        String patchFrom = HttpRequest.getRemoteHost();
        String patch = HttpRequest.getRequestURI();

        byte[] body = null;
        if(headers.get("content-type").contains("multipart/form-data;")) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) HttpRequest;
            for(Map.Entry entry:  multipartRequest.getFileMap().entrySet()) {
                body = ((MultipartFile)entry.getValue()).getBytes();
            }
        }else {
            body = HttpRequest.getReader().lines().collect(Collectors.joining()).getBytes();
        }


        Request request = new Request(uuid, new Date().getTime(), patchFrom, patch, headers, parameters, body);
        repositoryRequest.save(request);

        History history = new History(uuid, new Date(), patchFrom, patch);
        repositoryHistory.save(history);


        if (config.isEnableAutoSend()){
            try {
                HttpURLConnection sendRemoteSystem = RemoteSystem.sendRequest(request, config.getPathRemoteSystem());
                if (sendRemoteSystem.getResponseCode()==200) {
                    history.setStatus(History.STATUS.send);
                    repositoryHistory.save(history);
                    repositoryRequest.delete(request);
                }
            } catch (Exception e) {
                history.setStatus(History.STATUS.error);
                repositoryHistory.save(history);
                e.printStackTrace();
            }
        }

        HttpResponse.setStatus(200);

    }

    @RequestMapping(path = "/remotesystem/givenew", method = RequestMethod.POST)
    public void takeRequest(HttpServletResponse servletResponse){

        Request request = repositoryRequest.findTop1ByTimeStampNotNullOrderByTimeStamp();

        if (request != null){
            RemoteSystem.fillHttpResponseByRequestData(servletResponse, request);
        }

    }

}