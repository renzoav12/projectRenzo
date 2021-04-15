package com.lickvalue.change.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 "symbol":  "1INCH",
 "longoffset":  "6.5",
 "shortoffset":  "6.5",
 "lickvalue":  "1019",
 "var_enabled":  true,
 "var_staticList":  true,
 "var_whiteList":  true,
 "var_blackList":  false,
 "tmp_kline_age":  "",
 "tmp_color":  "Black"
 */

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
