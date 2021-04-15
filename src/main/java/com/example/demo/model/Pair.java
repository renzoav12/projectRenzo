package com.example.demo.model;
/**
 * symbol: "IOTA",
 * total_events: 153,
 * total_usdt: 205728.26,
 * average_usdt: 1344.63,
 * average_amount: "673.6673202614379085"
 * },
 */
public class Pair {

    private  String symbol;
    private  Double average_usdt;
    private  String average_amount;
    private  Integer total_events;
    private  Double total_usdt;


    public String getSymbol() {
        return symbol;
    }

    public Double getAverage_usdt() {
        return average_usdt;
    }

    public String getAverage_amount() {
        return average_amount;
    }

    public Integer getTotal_events() {
        return total_events;
    }

    public Double getTotal_usdt() {
        return total_usdt;
    }
}
