package com.example.marin.ms_pb_17.Currency;

/**
 * Created by marina on 24/11/2017.
 */

public class Currency {

    private String name;
    private double rate;

    public Currency(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}