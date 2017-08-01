package com.example.sitecrawler;

import org.apache.http.HttpStatus;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {

    private static final String bar = "##################################################";

    public static void main(String[] args) {
        System.out.println(bar);
        System.out.println("Enter the URL of the website you want to crawl :");

        Scanner in = new Scanner(System.in);
        String url = in.next();

        System.out.println(String.format("Connecting to %s ...", url));
        HttpResponse<String> response = CrwalerHttp.get(url).asString();
        if(response.getStatus() == HttpStatus.SC_OK) {
            System.out.println("Success to connecting");
            System.out.println("Parsing to response body ...");
            List<String> imageList = CrawlerDomParser.findImageSrcList(response.getBody());
            System.out.println("Crawling images ...");
            imageList.stream().forEach(n -> {
                CrawlerDownloader.downloadImages(n, "./images", (filename, total, current, status) -> {
                    if(status == HttpStatus.SC_OK) {
                        CrawlerConsoleProgressBar.updateProgressBar(filename, current * 100 / total);
                    }
                    else {
                        System.out.println("Error : " + n);
                    }

                }) ;
                System.out.println("");
               
            });


        }
        else {
            System.out.println("Failed to connect url");
        }




        System.out.println("");
        System.out.println("\r\nDone");
        System.out.println(bar);

    }
}
