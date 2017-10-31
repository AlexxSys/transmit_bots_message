package ru.alexxsys.transmit_bots_message.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.alexxsys.transmit_bots_message.configuration.ConfigTransferSystem;
import ru.alexxsys.transmit_bots_message.entity.Request;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RemoteSystem {

    public static void fillHttpResponseByRequestData(ServletResponse servletResponse, Request request){

//        for (Map.Entry<String, String> headerElement : request.getHeaders().entrySet()) {
//            HttpResponse.addHeader(headerElement.getKey(), headerElement.getValue());
//        }
//
//        try {
//            PrintWriter bodyRequest = HttpResponse.getWriter();
//            bodyRequest.write(request.getBody());
//            bodyRequest.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static HttpURLConnection sendRequest(Request request, String urlWithoutParam, String login, String password) throws Exception {

        StringBuilder url = new StringBuilder(urlWithoutParam);
        url.append(request.getPatch());

        boolean isBeginParametrs = true;
        for (Map.Entry<String, String> headerElement : request.getParametrs().entrySet()) {
            url.append((isBeginParametrs)?"?":"&");
            url.append(headerElement.getKey());
            url.append("=");
            url.append(headerElement.getValue());
            isBeginParametrs = false;
        }

        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url.toString()).openConnection();
        httpConnection.setRequestMethod("POST");
        if (login != null && !login.equals("")) {
            String authorization = new String(new BASE64Decoder().decodeBuffer(login + ":" + password));
            httpConnection.setRequestProperty("Authorization", "Basic " + authorization);
        }

        for (Map.Entry<String, String> headerElement : request.getHeaders().entrySet()) {
            httpConnection.setRequestProperty(headerElement.getKey(), headerElement.getValue());
        }

        httpConnection.setDoOutput(true);
        DataOutputStream body = new DataOutputStream(httpConnection.getOutputStream());
        body.write(request.getBody());
        body.flush();
        body.close();

        httpConnection.connect();
        httpConnection.getResponseCode();

        return httpConnection;

    }

}
