package com.kavishaparanamana.quoteofthedayapp.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.kavishaparanamana.quoteofthedayapp.repositories.Contents;
import com.kavishaparanamana.quoteofthedayapp.repositories.Copyright;
import com.kavishaparanamana.quoteofthedayapp.repositories.Quote;
import com.kavishaparanamana.quoteofthedayapp.repositories.Response;
import com.kavishaparanamana.quoteofthedayapp.repositories.Success;


import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class JsonDeserializerQuote implements JsonDeserializer<Response> {

   /* public Quote deserialize(JsonElement json, Type typeOfT,
                             JsonDeserializationContext context) throws JsonParseException {

        JsonObject quoteJson = json.getAsJsonObject();
        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            date = sdf.parse(quoteJson.get("date").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<String> tagList = new ArrayList<>();

        JsonArray array = quoteJson.getAsJsonArray("tags");

        for (int i = 0; i < array.size(); i++) {
            tagList.add(array.get(i).getAsString());
        }


        Quote quote = new Quote();
        quote.setQuote(quoteJson.get("quote").getAsString());
        quote.setLength(quoteJson.get("length").getAsString());
        quote.setAuthor(quoteJson.get("author").getAsString());
        quote.setTags(tagList);
        quote.setCategory(quoteJson.get("category").getAsString());
        quote.setLanguage(quoteJson.get("language").getAsString());
        quote.setDate(date);
        quote.setPermalink(quoteJson.get("permalink").getAsString());
        quote.setId(quoteJson.get("id").getAsInt());
        quote.setBackground(quoteJson.get("background").getAsString());
        quote.setTitle(quoteJson.get("title").getAsString());
        return quote;
    }*/


    public Response deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        Response reponseObj = new Response();
        reponseObj.setBaseurl(jsonObject.get("baseurl").getAsString());


        JsonObject copyrightJsonObject = jsonObject.get("copyright").getAsJsonObject();
        Copyright copyrightObj = new Copyright();
        copyrightObj.setUrl(copyrightJsonObject.get("url").getAsString());
        copyrightObj.setYear(copyrightJsonObject.get("year").getAsString());
        reponseObj.setCopyright(copyrightObj);

        JsonObject SuccessJsonObject = jsonObject.get("success").getAsJsonObject();
        Success successObj = new Success();
        successObj.setTotal(SuccessJsonObject.get("total").getAsInt());
        reponseObj.setSuccess(successObj);

        JsonObject contentsJsonObject = jsonObject.get("contents").getAsJsonObject();
        Contents contentObj = new Contents();
        JsonArray quotesJsonAarry = contentsJsonObject.get("quotes").getAsJsonArray();

        ArrayList<Quote> quotesArray = new ArrayList<>();

        for (int i = 0; i < quotesJsonAarry.size(); i++) {
            Quote qo = getQuoteObject(quotesJsonAarry.get(i).getAsJsonObject());
            quotesArray.add(qo);
        }

        contentObj.setQuotes(quotesArray);
        reponseObj.setContents(contentObj);
        return reponseObj;
    }


    private Quote getQuoteObject(JsonObject jsonObject) {

        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            date = sdf.parse(jsonObject.get("date").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<String> tagList = new ArrayList<>();

        JsonArray array = jsonObject.getAsJsonArray("tags");

        for (int i = 0; i < array.size(); i++) {
            tagList.add(array.get(i).getAsString());
        }


        Quote quote = new Quote();
        quote.setQuote(jsonObject.get("quote").getAsString());
        quote.setLength(jsonObject.get("length").getAsString());
        quote.setAuthor(jsonObject.get("author").getAsString());
        quote.setTags(tagList);
        quote.setCategory(jsonObject.get("category").getAsString());
        quote.setLanguage(jsonObject.get("language").getAsString());
        quote.setDate(date);
        quote.setPermalink(jsonObject.get("permalink").getAsString());
        quote.setId(jsonObject.get("id").getAsInt());
        quote.setBackground(jsonObject.get("background").getAsString());
        quote.setTitle(jsonObject.get("title").getAsString());

        return quote;
    }


}
