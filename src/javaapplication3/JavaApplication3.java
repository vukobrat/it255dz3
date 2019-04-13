/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author marko
 */
public class JavaApplication3 {

    
    public static void main(String[] args) {
    
        User user = new User();
        
        user.setEmail("mmmasdaarko@brat.com");
        user.setEmailConfirmationCode("13234");
        user.setEmailConfirmed(true);
        user.setFullName("Marksso201");
        user.setPassword("Okssejk");
        
        try {
            URL url = new URL("http://89.216.56.107:8080/restfull/rest/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Greška : HTTP error: "+conn.getResponseCode());
            }
        
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(new Gson().toJson(user));
            pw.close();
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    
}
