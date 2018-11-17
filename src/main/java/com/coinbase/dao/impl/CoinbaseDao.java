package com.coinbase.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinbase.Job;
import com.coinbase.constants.Constants;
import com.coinbase.dao.AppDao;
import com.coinbase.models.BTCData;
import com.coinbase.utils.Algorithm;
import com.coinbase.utils.DataHolding;
import com.coinbase.utils.DataValidationException;
import com.coinbase.utils.Utility;
import com.coinbase.utils.Validator;

public class CoinbaseDao implements AppDao {

	Utility utils = new Utility();
	Validator validator = new Validator();
	final Logger LOGGER = LoggerFactory.getLogger(Job.class);
	DataHolding dataHolding = DataHolding.getInstance();
	Algorithm algo = new Algorithm();

	public void refreshBitcoinData() throws DataValidationException {
		try {
			LOGGER.info("Getting data from coinbase URL ");
			String jsonData = utils.readUrl(Constants.coinBaseUrl);
			dataHolding.updateData(jsonData);

		} catch (Exception e) {
			LOGGER.error("Data exception while accessing coinbase url" + e.getMessage());
			throw new DataValidationException("Data exception while accessing coinbase url", e.getMessage());
		}
	}

	@Override
	public BTCData getPriceByDates(Date startDate, Date endDate) throws DataValidationException {
		if (!validator.dataHoldingValid(dataHolding.getLastDate())) {
			LOGGER.info("Updating Dataholding for data");
			this.refreshBitcoinData();
		}
		return utils.filterDataByDates(startDate, endDate, this.dataHolding.getData());

	}

	@Override
	public BTCData getPriceByType(String type) throws DataValidationException {
		ArrayList<Date> dates = utils.getDatesForType(type);
		BTCData data = this.getPriceByDates(dates.get(0), dates.get(1));
		System.out.println(data);
		return data;

	}

	@Override
	public float getBitcoinMovingAverage(String days) throws DataValidationException {
		ArrayList<Date> dates = utils.getLastXDays(days);
		return this.getBitcoinMovingAverage(dates.get(0), dates.get(1));

	}

	@Override
	public float getBitcoinMovingAverage(Date startDate, Date endDate) throws DataValidationException {
		BTCData data = this.getPriceByDates(startDate, endDate);

		long diff = endDate.getTime() - startDate.getTime();

		return algo.getMovingAverage(data.getData().getPriceList(), TimeUnit.MILLISECONDS.toDays(diff));

	}

	@Override
	public String getTradeDecision(String days) throws DataValidationException {
		ArrayList<Date> dates = utils.getLastXDays(days);
		BTCData data = this.getPriceByDates(dates.get(0), dates.get(1));
		long diff = dates.get(0).getTime() - dates.get(1).getTime();
		return algo.getTradeDecision(data.getData().getPriceList(),
				algo.getMovingAverage(data.getData().getPriceList(), TimeUnit.MILLISECONDS.toDays(diff)));
	}

}
