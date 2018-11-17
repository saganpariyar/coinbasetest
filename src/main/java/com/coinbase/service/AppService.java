package com.coinbase.service;

import java.text.ParseException;

import com.coinbase.models.BTCData;
import com.coinbase.utils.DataValidationException;

public interface AppService {
	BTCData getPriceByDates(String startDate, String endDate) throws DataValidationException, ParseException;

	BTCData getPriceByType(String type) throws DataValidationException;

	float getBitcoinMovingAverage(String start_date, String end_date) throws DataValidationException, ParseException;

	float getBitcoinMovingAverage(String days) throws DataValidationException;

	String getTradeDecision(String days) throws DataValidationException;;

}
