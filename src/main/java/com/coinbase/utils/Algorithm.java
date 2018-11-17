package com.coinbase.utils;

import java.util.ArrayList;

public class Algorithm {

	public float getMovingAverage(ArrayList<Float> dataList, long count) {
		float total = 0;
		for (float value : dataList) {
			total = total + value;
		}
		return total / count;
	}

	public String getTradeDecision(ArrayList<Float> priceList, float movingAvg) {
		float indexPrice = 1.2f;
		if (priceList.get(0) > movingAvg * indexPrice) {
			return TradingDecision.BUY.toString();
		} else if (priceList.get(0) < movingAvg * indexPrice) {
			return TradingDecision.SELL.toString();
		} else {
			return TradingDecision.HOLD.toString();
		}
	}
}
