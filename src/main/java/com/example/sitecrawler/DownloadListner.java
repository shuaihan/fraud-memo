package com.example.sitecrawler;

@FunctionalInterface
public interface DownloadListner {
     void callback(long total, long current, int status);

}
