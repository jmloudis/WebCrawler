/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author jordan
 */
public class Crawler
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        String url = "https://en.wikipedia.org/";

//      # Create a recurssive function
        crawl(1, url, new ArrayList<String>());
    }
    
    public static void crawl(int level, String url, ArrayList<String> visited)
    {
        if(level <= 5)
        {
            Document doc = request(url, visited);
            
            if (doc != null)
            {
                for(Element link: doc.select("a[href]"))
                {
                    String next_link = link.absUrl("href");
                    if(visited.contains(next_link) == false)
                    {
                        crawl(level++, next_link, visited);
                    }
                }
            }
        }
        
        
    }
    
    private static Document request(String url, ArrayList<String> v)
    {
        try{
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            
            if(con.response().statusCode() == 200)
            {
                System.out.println("Link: " + url);
                System.out.println(doc.title());
                v.add(url);
                
                return doc;
            }
            return doc;
        }
        
        catch(IOException e)
        {
            return null;
            
        }
        
    }

    
}
