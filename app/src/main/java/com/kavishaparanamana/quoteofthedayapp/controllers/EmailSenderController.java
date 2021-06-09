package com.kavishaparanamana.quoteofthedayapp.controllers;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kavishaparanamana.quoteofthedayapp.R;
import com.kavishaparanamana.quoteofthedayapp.models.JsonDeserializerQuote;
import com.kavishaparanamana.quoteofthedayapp.repositories.Contents;
import com.kavishaparanamana.quoteofthedayapp.repositories.Copyright;
import com.kavishaparanamana.quoteofthedayapp.repositories.Quote;
import com.kavishaparanamana.quoteofthedayapp.repositories.Response;
import com.kavishaparanamana.quoteofthedayapp.utilities.API;
import com.kavishaparanamana.quoteofthedayapp.utilities.GMailSender;

import org.json.JSONArray;
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

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailSenderController {

    public void getQuote(){
   // public Quote getQuote(){
//Quote quoteObj=new Quote();
            String content =getJSON(API.url);

       try{
           JSONObject nodeRoot  = new JSONObject(content);
           JSONObject nodeContents = nodeRoot.getJSONObject("contents");
           JSONArray array= nodeContents.getJSONArray("quotes");

           JSONObject node=array.getJSONObject(0);

           Gson gson = new Gson();

           Response quoteObj = gson.fromJson(content, Response.class);

        //   System.out.println("TEST quote done node title "+node.get("title"));
           System.out.println("TEST quote done Baseurl "+ quoteObj.getBaseurl());
           System.out.println("TEST quote done total "+ quoteObj.getSuccess().getTotal());

       }catch (Exception e){

       }
      // return
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
            System.out.println("TEST  status"+status);
            switch (status) {
                case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
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




    public void sendEmail(String[] emails, String subject,String body, boolean forceGmail,String user, String pass) {
        try {
            for(int i=0; i < emails.length;i++){
                if(isValidEmailAddress(emails[i])){
                    GMailSender sender = new GMailSender(user, pass);
                    sender.sendMail(subject,
                            body,
                            user,
                            emails[i]);
                }

            }

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }

    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}
