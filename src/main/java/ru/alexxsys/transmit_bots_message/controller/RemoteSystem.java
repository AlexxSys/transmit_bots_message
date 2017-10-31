package ru.alexxsys.transmit_bots_message.controller;

import ru.alexxsys.transmit_bots_message.entity.Request;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RemoteSystem {

    public static void fillHttpResponseByRequestData(HttpServletResponse servletResponse, Request request){

        // заполнение заголовка
        for (Map.Entry<String, String> headerElement : request.getHeaders().entrySet()) {
            servletResponse.setHeader(headerElement.getKey(), headerElement.getValue());
        }

        // заполнение тела
        try {
            ServletOutputStream outputStreamBody = servletResponse.getOutputStream();
            outputStreamBody.write(request.getBody());
            outputStreamBody.flush();
            outputStreamBody.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static HttpURLConnection sendRequest(Request request, String urlWithoutParam, String login, String password) throws Exception {

        StringBuilder url = new StringBuilder(urlWithoutParam);
        url.append(request.getPatch());

        // заполнение параметров
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

        // заполнение заголовка
        for (Map.Entry<String, String> headerElement : request.getHeaders().entrySet()) {
            httpConnection.setRequestProperty(headerElement.getKey(), headerElement.getValue());
        }

        // заполнение тела
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
