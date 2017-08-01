package com.example.sitecrawler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CrawlerDownloaderTest {

    @Before
    public void before() {

    }


    @Test
    public void test_download() {
        Assert.assertTrue(CrawlerDownloader.downloadImages(
                "https://static.squarespace.com/universal/images-v6/configuration/placeholder/3x4-image-7-half-color.jpg",
                "./" ,
                (total, current, status) -> {
                     System.out.println(String.format("%d=%d=%d", total, current, status));
                })) ;
    }

    @After
    public void after() {

    }
}