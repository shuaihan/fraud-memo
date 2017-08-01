package com.example.sitecrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpResponse<T> {
    private int statusCode;
    private String statusText;
    private InputStream rawBody;
    private T body;

    public HttpResponse(HttpURLConnection con) {

        try {
            this.statusCode = con.getResponseCode();
            this.statusText = con.getResponseMessage();
            BufferedReader br = null ;
            if(statusCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            this.body = (T) response.toString();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getStatus() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public InputStream getRawBody() {
        return rawBody;
    }

    public T getBody() {
        return body;
    }


    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", statusText='" + statusText + '\'' +
                ", rawBody=" + rawBody +
                ", body=" + body +
                '}';
    }
}
