package com.lickvalue.change.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coin {

    private String symbol;
    private String longoffset;
    private String shortoffset;
    private String lickvalue;
    private Boolean var_enabled;
    private Boolean var_staticList;
    private Boolean var_whiteList;
    private Boolean var_blackList;
    private String tmp_kline_age;
    private String tmp_color;
}
