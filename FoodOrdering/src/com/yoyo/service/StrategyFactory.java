package com.yoyo.service;

import com.yoyo.exception.InvalidInputException;

public class StrategyFactory {

    public static SelectionStrategy get(String strategy) {

        switch (strategy) {
            case "lowest_price_strategy":
                return new LowestPriceStrategy();
            default:
                throw new InvalidInputException(String.format("strategy %s not supported", strategy));
        }
    }

}
