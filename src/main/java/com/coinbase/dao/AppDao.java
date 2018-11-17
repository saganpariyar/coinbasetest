package com.coinbase.dao;

import java.util.Date;

import com.coinbase.models.BTCData;
import com.coinbase.utils.DataValidationException;

public interface AppDao {

	BTCData getPriceByType(String type) throws DataValidationException;

	float getBitcoinMovingAverage(Date startDate, Date endDate) throws DataValidationException;

	BTCData getPriceByDates(Date startDate, Date endDate) throws DataValidationException;

	float getBitcoinMovingAverage(String days) throws DataValidationException;

	String getTradeDecision(String days) throws DataValidationException;
}
