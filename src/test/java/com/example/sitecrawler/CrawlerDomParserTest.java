package com.example.sitecrawler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CrawlerDomParserTest {

    @Before
    public void before() {

    }

    @Test
    public void test_parseImgSrc() {
        List<String> imgSrc = CrawlerDomParser.findImageSrcList(
                CrwalerHttp.get("http://www.imagegallery.nyc/exhitions/").asString().getBody()) ;
        Assert.assertTrue(!imgSrc.isEmpty());
        System.out.println(imgSrc);
    }
 

    @After
    public void after() {

    }
}