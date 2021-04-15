package com.lickvalue.change.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Data {

    @JsonProperty("data")
    private List<Pair> data;

    public Data() {
    }
}
