package com.example.sitecrawler;

import org.apache.http.HttpStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CrawlerDownloader {


    public static  boolean downloadImages(String imageUrl, String saveDir, DownloadListner listner) {
        //Exctract the name of the image from the src attribute
        
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();
                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10,
                                disposition.length() - 1);
                    }
                } else {
                    // extracts file name from URL
                    int indexname = imageUrl.lastIndexOf("/");
                    if (indexname == imageUrl.length()) {
                        imageUrl = imageUrl.substring(1, indexname);
                    }
                    indexname = imageUrl.lastIndexOf("/");
                    fileName= imageUrl.substring(indexname, imageUrl.length());
                }
                System.out.println("Content-Type = " + contentType);
                System.out.println("Content-Disposition = " + disposition);
                System.out.println("Content-Length = " + contentLength);
                System.out.println("fileName = " + fileName);
                listner.callback(contentLength, 0, HttpStatus.SC_OK);
                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;
                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                int currentBytesRead = 0;
                int bytesRead = -1;
                byte[] buffer = new byte[1024];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    currentBytesRead +=  bytesRead;
                    listner.callback(contentLength, currentBytesRead, HttpStatus.SC_OK);
                }

                outputStream.close();
                inputStream.close();

                System.out.println("File downloaded");
                return true;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listner.callback(0, 0, HttpStatus.SC_SERVICE_UNAVAILABLE);
        return false;
    }
}
