package com.example.demo.model;

import java.util.List;

public class VarPair {

    private String botName;
    private String maxPairs;
    private String maxOpen;
    private String openOrderIsolationPercentage;
    private String tradingMode;
    private String tradePairs;
    private String fundingRateThreshold;
    private String maxFundingRate;
    private String tradingAge;
    private String marginType;
    private String refreshTime;
    private String lhpcKey;
    private List<Coin> coins;


    public VarPair(String botName, String maxPairs, String maxOpen, String openOrderIsolationPercentage,
                   String tradingMode, String tradePairs, String fundingRateThreshold,
                   String maxFundingRate, String tradingAge, String marginType, String refreshTime,
                   String lhpcKey, List<Coin> coins) {
        this.botName = botName;
        this.maxPairs = maxPairs;
        this.maxOpen = maxOpen;
        this.openOrderIsolationPercentage = openOrderIsolationPercentage;
        this.tradingMode = tradingMode;
        this.tradePairs = tradePairs;
        this.fundingRateThreshold = fundingRateThreshold;
        this.maxFundingRate = maxFundingRate;
        this.tradingAge = tradingAge;
        this.marginType = marginType;
        this.refreshTime = refreshTime;
        this.lhpcKey = lhpcKey;
        this.coins = coins;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getMaxPairs() {
        return maxPairs;
    }

    public void setMaxPairs(String maxPairs) {
        this.maxPairs = maxPairs;
    }

    public String getMaxOpen() {
        return maxOpen;
    }

    public void setMaxOpen(String maxOpen) {
        this.maxOpen = maxOpen;
    }

    public String getOpenOrderIsolationPercentage() {
        return openOrderIsolationPercentage;
    }

    public void setOpenOrderIsolationPercentage(String openOrderIsolationPercentage) {
        this.openOrderIsolationPercentage = openOrderIsolationPercentage;
    }

    public String getTradingMode() {
        return tradingMode;
    }

    public void setTradingMode(String tradingMode) {
        this.tradingMode = tradingMode;
    }

    public String getTradePairs() {
        return tradePairs;
    }

    public void setTradePairs(String tradePairs) {
        this.tradePairs = tradePairs;
    }

    public String getFundingRateThreshold() {
        return fundingRateThreshold;
    }

    public void setFundingRateThreshold(String fundingRateThreshold) {
        this.fundingRateThreshold = fundingRateThreshold;
    }

    public String getMaxFundingRate() {
        return maxFundingRate;
    }

    public void setMaxFundingRate(String maxFundingRate) {
        this.maxFundingRate = maxFundingRate;
    }

    public String getTradingAge() {
        return tradingAge;
    }

    public void setTradingAge(String tradingAge) {
        this.tradingAge = tradingAge;
    }

    public String getMarginType() {
        return marginType;
    }

    public void setMarginType(String marginType) {
        this.marginType = marginType;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getLhpcKey() {
        return lhpcKey;
    }

    public void setLhpcKey(String lhpcKey) {
        this.lhpcKey = lhpcKey;
    }
}
