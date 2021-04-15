package com.lickvalue.change.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * symbol: "IOTA",
 * total_events: 153,
 * total_usdt: 205728.26,
 * average_usdt: 1344.63,
 * average_amount: "673.6673202614379085"
 * },
 */
@Getter
@Setter
public class Pair {

    private  String symbol;
    private  Double average_usdt;
    private  String average_amount;
    private  Integer total_events;
    private  Double total_usdt;
}
