package com.example.demo.model;

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

    public Coin(String symbol, String longoffset, String shortoffset, String lickvalue,
                Boolean var_enabled, Boolean var_staticList,
                Boolean var_whiteList, Boolean var_blackList, String tmp_kline_age, String tmp_color) {
        this.symbol = symbol;
        this.longoffset = longoffset;
        this.shortoffset = shortoffset;
        this.lickvalue = lickvalue;
        this.var_enabled = var_enabled;
        this.var_staticList = var_staticList;
        this.var_whiteList = var_whiteList;
        this.var_blackList = var_blackList;
        this.tmp_kline_age = tmp_kline_age;
        this.tmp_color = tmp_color;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLongoffset() {
        return longoffset;
    }

    public void setLongoffset(String longoffset) {
        this.longoffset = longoffset;
    }

    public String getShortoffset() {
        return shortoffset;
    }

    public void setShortoffset(String shortoffset) {
        this.shortoffset = shortoffset;
    }

    public String getLickvalue() {
        return lickvalue;
    }

    public void setLickvalue(String lickvalue) {
        this.lickvalue = lickvalue;
    }

    public Boolean getVar_enabled() {
        return var_enabled;
    }

    public void setVar_enabled(Boolean var_enabled) {
        this.var_enabled = var_enabled;
    }

    public Boolean getVar_staticList() {
        return var_staticList;
    }

    public void setVar_staticList(Boolean var_staticList) {
        this.var_staticList = var_staticList;
    }

    public Boolean getVar_whiteList() {
        return var_whiteList;
    }

    public void setVar_whiteList(Boolean var_whiteList) {
        this.var_whiteList = var_whiteList;
    }

    public Boolean getVar_blackList() {
        return var_blackList;
    }

    public void setVar_blackList(Boolean var_blackList) {
        this.var_blackList = var_blackList;
    }

    public String getTmp_kline_age() {
        return tmp_kline_age;
    }

    public void setTmp_kline_age(String tmp_kline_age) {
        this.tmp_kline_age = tmp_kline_age;
    }

    public String getTmp_color() {
        return tmp_color;
    }

    public void setTmp_color(String tmp_color) {
        this.tmp_color = tmp_color;
    }
}
