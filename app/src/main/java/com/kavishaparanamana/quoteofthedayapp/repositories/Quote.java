package com.kavishaparanamana.quoteofthedayapp.repositories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Quote {
    private String quote;
    private String length;
    private String author;
    private List<String> tags;
    private String category;
    private String language;
    private Date date;
    private String permalink;
    private int id;
    private String background;
    private String title;



    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }



    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /*@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a", Locale.ENGLISH);

        builder.append("Id: ").append(id).append(", ")
                .append("First Name: ").append(firstName).append(", ")
                .append("Last Name: ").append(lastName).append(", ")
                .append("Date: ").append(sdf.format(date)).append(", ")
                .append("Photo path: ").append(photo).append(", ")
                .append("Is Married: ").append(married);
        return builder.toString();
    }*/
}



