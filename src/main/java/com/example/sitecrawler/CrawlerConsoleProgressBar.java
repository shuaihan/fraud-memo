package com.example.sitecrawler;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrawlerConsoleProgressBar {

    public static void updateProgressBar(String fileName, long progress) {

        System.out.print('\r');
        final long sharpCount = progress / 2;
        String progressMark = Stream.iterate(0, n -> n + 1)
                .limit(50)
                .map(n -> sharpCount >= n ? "#" : " ")
                .collect(Collectors.joining());
        System.out.print(String.format("%s %02d%% [%s] %d/100", fileName, progress, progressMark, progress));


    }
}
