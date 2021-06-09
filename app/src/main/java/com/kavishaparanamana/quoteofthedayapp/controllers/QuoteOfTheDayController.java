package com.kavishaparanamana.quoteofthedayapp.controllers;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kavishaparanamana.quoteofthedayapp.R;
import com.kavishaparanamana.quoteofthedayapp.models.JsonDeserializerQuote;
import com.kavishaparanamana.quoteofthedayapp.repositories.Contents;
import com.kavishaparanamana.quoteofthedayapp.repositories.Quote;
import com.kavishaparanamana.quoteofthedayapp.repositories.Response;
import com.kavishaparanamana.quoteofthedayapp.utilities.API;
import com.kavishaparanamana.quoteofthedayapp.utilities.GMailSender;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuoteOfTheDayController {

   // public Quote getQuote(){
   public void getQuote(){
            String content =getJSON(API.url);
            Gson gson = new GsonBuilder().registerTypeAdapter(Response.class, new JsonDeserializerQuote())
                    .serializeNulls().create();

           // Quote quote = gson.fromJson(content, new TypeToken<Quote>(){}.getType());
            System.out.println("TEST after "+content);
            Response response =gson.fromJson(content, new TypeToken<Response>(){}.getType());
            System.out.println("TEST quote title +++ "+response.getContents().getQuotes().get(0).getTitle());

         //   return  response.getContents().getQuotes().get(0);
    }


    private String getJSON(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
           /* c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);*/
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private static String fetchContent(String uri) throws IOException {
        System.out.println("TEST  fetchContent");
        final int OK = 200;
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String returnObj;

        int responseCode = connection.getResponseCode();
        System.out.println("TEST done"+responseCode);
        if(responseCode == OK){
            System.out.println("TEST OK");


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            System.out.println("TEST done"+sb.toString());
            return sb.toString();
        }
        System.out.println("TEST null"+responseCode);
        return null;
    }


    public void sendEmail(String[] emails, String subject,String body, boolean forceGmail,String user, String pass) {
        try {
            for(int i=0; i < emails.length;i++){
                GMailSender sender = new GMailSender(user, pass);
                sender.sendMail(subject,
                        body,
                        user,
                        emails[i]);
            }

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }

    }

}
