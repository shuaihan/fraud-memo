package com.example.sitecrawler;

@FunctionalInterface
public interface DownloadListner {
     void callback(String filename, long total, long current, int status);

}
