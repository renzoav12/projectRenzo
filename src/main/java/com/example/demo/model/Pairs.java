package com.example.demo.model;

import java.util.List;

public class Pairs {

    private List<Coin> coins;

    public Pairs(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Coin> getCoinsList() {
        return coins;
    }

    public void setCoinsList(List<Coin> coins) {
        this.coins = coins;
    }
}
