package com.wys.keelong;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.*;
import java.io.IOException;

public class KeelungSightsCrawler {

    private static final String MAIN_URL = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/";

    public Sight[] getItems(String zoneZH){
        try {
            List<Sight> sights = new ArrayList<>();
            Document document = Jsoup.connect(MAIN_URL).get();

            Elements zoneElements = document.select("#guide-point > div > h4");
            for (Element zoneElement : zoneElements){
                String zone = zoneElement.text();
                if (zone.contains(zoneZH)){
                    List<String> sightURLs = new ArrayList<>();
                    try {
                        Element ul = zoneElement.nextElementSibling();
                        Elements links = ul.select("a");
                        for (Element link : links) {
                            String url = link.absUrl("href");
                            sightURLs.add(url);
                        }
                    } catch (NullPointerException e){
                        System.out.println("獲取景點網址失敗:" + e.getMessage());
                    }

                    for (String url : sightURLs){
                        Sight sight = this.getItem(url, zone);
                        if (sight != null) {
                            sights.add(sight);
                        }
                    }
                    break;
                }
            }
            return sights.toArray(new Sight[0]);//TOKNOW
        } catch(IOException e){
            System.out.println("主葉面失敗:" + e.getMessage());
        }
        return new Sight[]{};
    }

    public Sight getItem(String url, String zone){
        try {
            Document document = Jsoup.connect(url).get();

            //sightName
            Element sightNameElement = document.selectFirst("#point_area > h1 > span");
            String sightName = (sightNameElement != null) ? sightNameElement.text() : "";
            //System.out.println(sightName);

            //category
            Element categoryElement = document.selectFirst("#point_area > cite > span.point_type > span:nth-child(2) > strong");
            String category = (categoryElement != null) ? categoryElement.text() : "";
            //System.out.println(category);

            //photoURL
            Element photoElement = document.selectFirst("#galleria img");
            String photoURL = (photoElement != null) ? photoElement.attr("src") : "fbfbd";
            //System.out.println(photoURL);

            //description
            Element descriptionElement = document.selectFirst("#point_area > div.text");
            String description = (descriptionElement != null) ? descriptionElement.text() : "";
            //.out.println(description);

            //address
            Element addressElement = document.selectFirst("[property=vcard:street-address]");
            String address = (addressElement != null) ? addressElement.text() : "";
            //System.out.println(address);

            return Sight.builder()
                .sightName(sightName)
                .zone(zone)
                .category(category)
                .photoURL(photoURL)
                .description(description)
                .address(address)
                .build();

        } catch (IOException e) {
            System.out.println("失敗:" + e.getMessage());
            return null;
        }
    }
}
