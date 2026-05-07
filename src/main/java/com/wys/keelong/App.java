package com.wys.keelong;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight [] sights = crawler.getItems("中山");
        for (Sight s: sights) {
            System.out.println(s);
        }
    }
}
