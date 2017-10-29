package ru.alexxsys.transmit_bots_message.controller;

import com.sun.deploy.net.HttpResponse;
import ru.alexxsys.transmit_bots_message.entity.Request;

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

    public static HttpURLConnection sendRequest(Request request, String urlWithoutParam) throws Exception {

        StringBuilder url = new StringBuilder(urlWithoutParam );
        url.append("/");
        url.append(request.getPatch());
        for (Map.Entry<String, String> headerElement : request.getHeaders().entrySet()) {
            if ((urlWithoutParam.equals(url.toString()))) {
                url.append("?");
            } else {
                url.append("&");
            }
            url.append(headerElement.getKey());
            url.append("=");
            url.append(headerElement.getValue());
        }

        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url.toString()).openConnection();
        httpConnection.setRequestMethod("POST");

        for (Map.Entry<String, String> headerElement : request.getHeaders().entrySet()) {
            httpConnection.setRequestProperty(headerElement.getKey(), headerElement.getValue());
        }

        httpConnection.setDoOutput(true);
        DataOutputStream body = new DataOutputStream(httpConnection.getOutputStream());
        body.write(request.getBody());
        body.flush();
        body.close();

        httpConnection.connect();

        return httpConnection;

    }

}
