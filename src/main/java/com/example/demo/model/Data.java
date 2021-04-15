package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data {

    @JsonProperty("data")
    private List<Pair> data;

    public Data() {
    }

    public List<Pair> getData() {
        return data;
    }
}
