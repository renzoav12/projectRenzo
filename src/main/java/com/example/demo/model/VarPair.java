package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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

}
