package com.example.sitecrawler;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class CrwalerHttp {

    private HttpMethod method;
    private String url;

    private Map<String, String> queryStrings;
    private Map<String, String> headers;


    public CrwalerHttp(String url, HttpMethod method) {
          this.url = url;
          this.method = method;
    }

    public static CrwalerHttp get(String url) {
        return new CrwalerHttp(url, HttpMethod.GET);
    }

    public static CrwalerHttp post(String url) {
        return new CrwalerHttp(url, HttpMethod.POST);
    }

    public static CrwalerHttp put(String url) {
        return new CrwalerHttp(url, HttpMethod.PUT) ;
    }

    public static CrwalerHttp delete(String url) {
        return new CrwalerHttp(url, HttpMethod.DELETE);
    }

    public static CrwalerHttp patch(String url) {
        return new CrwalerHttp(url, HttpMethod.PATCH) ;
    }

    public static CrwalerHttp head(String url) {
        return new CrwalerHttp(url, HttpMethod.HEAD) ;
    }

    public static CrwalerHttp options(String url) {
        return new CrwalerHttp(url, HttpMethod.OPTIONS) ;
    }


    public CrwalerHttp queryString(String name, String value) {
        if(queryStrings == null) {
            queryStrings = new HashMap<>();
        }
        queryStrings.put(name, value);
        return this;
    }

    public CrwalerHttp header(String name, String value) {
        if(headers == null) {
            headers  = new HashMap<>();
        }
        queryStrings.put(name, value);
        return this;
    }

    public String buildQueryString() {

        if(queryStrings != null && !queryStrings.isEmpty()) {
            return  queryStrings.entrySet().stream()
                    .map(e -> {
                        return e.getKey() + '=' + e.getValue();
                    }).collect(Collectors.joining("&"));
        }

        return "";
    }


    public <T> HttpResponse<T> asString() {
        String apiURL = url + buildQueryString();


        try {
            URL url = new URL(apiURL);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod(method.name());
            if(headers != null) {
                headers.entrySet().stream().forEach(e -> {
                    con.setRequestProperty(e.getKey(), e.getValue());
                });
            }
            return new HttpResponse(con);

        } catch (IOException e) {

        }
        finally {

        }

        return new HttpResponse(null);


    }

    // String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
    /*
    public boolean requestGet(String url2, Map<String, String> param, Map<String, String> headers) {

        String queryString = param.entrySet().stream()
                .map( entry -> {
                   return entry.getKey() + '=' + entry.getValue();
                }).collect(Collectors.joining("&"));
        String clientId = "f_ux5OMP0fGE814CJvto";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "eoDR23pWo2";//애플리케이션 클라이언트 시크릿값";
        try {
            String apiURL;
            String text = URLEncoder.encode("그린팩토리", "UTF-8");
            if(url2.contains("?")) {
                apiURL = url2 + "&" + queryString;
            }
            else {
                apiURL = url2 + "?"  + queryString;
            }

            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }

        return true;
    }
    */

}
