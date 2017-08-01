package com.example.sitecrawler;

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



    @After
    public void after() {

    }
}