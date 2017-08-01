package com.example.sitecrawler;

import org.junit.*;

public class CrawlerDownloaderTest {

    @Before
    public void before() {

    }

    @Test
    public void test_filename() {
        // extracts file name from URL
        String imageUrl = "https://static1.squarespace.com/static/54aed895e4b0e60541923667/t/573cd4e245bf21cb7d557349/1463608187469/";
        int indexname = imageUrl.lastIndexOf("/");
        if (indexname == imageUrl.length()-1) {
            imageUrl = imageUrl.substring(1, indexname);
            System.out.println(imageUrl);
        }
        indexname = imageUrl.lastIndexOf("/");
        String fileName= imageUrl.substring(indexname, imageUrl.length());
        System.out.println(fileName);
    }


    @Test
    public void test_download() {
        Assert.assertTrue(CrawlerDownloader.downloadImages(
                "https://static1.squarespace.com/static/54aed895e4b0e60541923667/t/573cd4e245bf21cb7d557349/1463608187469/",
                "./images" ,
                (filaname, total, current, status) -> {
                     System.out.println(String.format("%d=%d=%d", total, current, status));
                })) ;
    }

    @After
    public void after() {

    }
}