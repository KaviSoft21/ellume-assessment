package com.kavishaparanamana.quoteofthedayapp.repositories;

import java.util.ArrayList;

public class Contents {
    private ArrayList<Quote> quotes = new ArrayList<Quote>();

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(ArrayList<Quote> quotes) {
        this.quotes = quotes;
    }


}