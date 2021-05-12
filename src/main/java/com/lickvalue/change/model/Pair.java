package com.lickvalue.change.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair {

    private  String symbol;
    private  Double average_usdt;
    private  String average_amount;
    private  Integer total_events;
    private  Double total_usdt;
}
