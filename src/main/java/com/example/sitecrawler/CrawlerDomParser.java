package com.example.sitecrawler;


import org.apache.commons.codec.binary.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;



public class CrawlerDomParser {


    public static List<String> findImageSrcList(String body) {

        List<String> imgSrc = new ArrayList<>();
        Document doc = Jsoup.parse(body);
        Elements img = doc.getElementsByTag("img");

        for (Element el : img) {
            String src = el.absUrl("src");
            if(org.apache.commons.lang3.StringUtils.isNotBlank(src)) {
                imgSrc.add(src);
            }
        }

        return imgSrc;
    }

}
