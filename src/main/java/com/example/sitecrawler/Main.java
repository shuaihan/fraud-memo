package com.example.sitecrawler;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static final String bar = "##################################################";

    public static void main(String[] args) {
        System.out.println(bar);
        System.out.println("Enter the URL of the website you want to crawl :");

        Scanner in = new Scanner(System.in);
        String url = in.next();

        System.out.println(String.format("Connecting to %s ...", url));

        System.out.println("Success to connecting");
        System.out.println("Parsing to response body ...");
        System.out.println("Crawling images ...");

        // file name 00% [#########           ] 3123/121233 
        for(int i = 0 ; i <= 100 ; i++) {
            System.out.print('\r');
            final int sharpCount = i/2;
            String progressMark = Stream.iterate(0, n -> n+1)
                    .limit(50)
                    .map(n -> sharpCount >= n ? "#" : " ")
                    .collect(Collectors.joining());
            System.out.print(String.format("file name %02d%% [%s] %d/100", i, progressMark, i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore
            }
        }
        System.out.println("");
        System.out.println("\r\nDone");
        System.out.println(bar);

    }
}
