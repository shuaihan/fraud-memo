package com.example.sitecrawler;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Response;

public class CrwalerHttpTest {
    @Before
    public void before() {

    }
    
    @Test
    public void test_QueryString() {
        CrwalerHttp crawlerHttp = CrwalerHttp.get("http://httpbin.org/post") ;

        Assert.assertEquals("", crawlerHttp.buildQueryString());
        crawlerHttp.queryString("key1", "value1");
        Assert.assertEquals("key1=value1", crawlerHttp.buildQueryString());

        crawlerHttp.queryString("key2", "value2");
        Assert.assertEquals("key1=value1&key2=value2", crawlerHttp.buildQueryString());
    }

    @Test
    public void test_get() {
        HttpResponse<String> response = CrwalerHttp.get("https://openapi.naver.com/v1/search/blog")
                .queryString("query", "kakao")
                .header("X-Naver-Client-Id", "f_ux5OMP0fGE814CJvto")
                .header("X-Naver-Client-Secret", "eoDR23pWo2")
                .asString();
        System.out.println(response);
        Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
        System.out.println(response.getBody());
    }



    @After
    public void after() {

    }
}