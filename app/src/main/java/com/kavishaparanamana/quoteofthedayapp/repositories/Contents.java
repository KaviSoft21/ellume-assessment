package com.kavishaparanamana.quoteofthedayapp.repositories;

import java.util.ArrayList;

public class Contents {
    private ArrayList<Quote> quotes = new ArrayList<Quote>();


    // Getter Methods

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }

    // Setter Methods

    public void setQuotes(ArrayList<Quote> quotes) {
        this.quotes = quotes;
    }


}