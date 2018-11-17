package com.coinbase.service.impl;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinbase.Job;
import com.coinbase.dao.AppDao;
import com.coinbase.dao.impl.CoinbaseDao;
import com.coinbase.models.BTCData;
import com.coinbase.service.AppService;
import com.coinbase.utils.DataValidationException;
import com.coinbase.utils.Messages;
import com.coinbase.utils.Utility;
import com.coinbase.utils.Validator;

public class CoinbaseService implements AppService {

	Utility utils = new Utility();
	AppDao dao = new CoinbaseDao();
	Validator validator = new Validator();
	final Logger LOGGER = LoggerFactory.getLogger(Job.class);

	@Override
	public BTCData getPriceByDates(String startDate, String endDate) throws DataValidationException, ParseException {
		if (!validator.validate(startDate, endDate)) {
			LOGGER.error(Messages.getString("CoinbaseService.error.message0")); //$NON-NLS-1$
			throw new DataValidationException(Messages.getString("CoinbaseService.error.message0")); //$NON-NLS-1$
		}

		return this.dao.getPriceByDates(utils.convertToDate(startDate), utils.convertToDate(endDate));
	}

	@Override
	public BTCData getPriceByType(String type) throws DataValidationException {
		if (!validator.validate(type)) {
			LOGGER.error(Messages.getString("CoinbaseService.error.message2")); //$NON-NLS-1$
			throw new DataValidationException(
					Messages.getString("CoinbaseService.error.message2")); //$NON-NLS-1$
		}
		return dao.getPriceByType(type);

	}

	@Override
	public String getTradeDecision(String days) throws DataValidationException {
		if (!validator.validateDays(days)) {
			LOGGER.error(Messages.getString("CoinbaseService.error.message4")); //$NON-NLS-1$
			throw new DataValidationException(Messages.getString("CoinbaseService.error.message4")); //$NON-NLS-1$
		}
		if (!validator.validateDaysForDecision(days)) {
			LOGGER.error(Messages.getString("CoinbaseService.error.message6")); //$NON-NLS-1$
			throw new DataValidationException(Messages.getString("CoinbaseService.error.message6")); //$NON-NLS-1$
		}
		return this.dao.getTradeDecision(days);
	}

	@Override
	public float getBitcoinMovingAverage(String days) throws DataValidationException {
		if (!validator.validateDays(days)) {
			LOGGER.error(Messages.getString("CoinbaseService.error.message8")); //$NON-NLS-1$
			throw new DataValidationException(Messages.getString("CoinbaseService.error.message8")); //$NON-NLS-1$
		}

		return this.dao.getBitcoinMovingAverage(days);
	}

	@Override
	public float getBitcoinMovingAverage(String startDate, String endDate)
			throws DataValidationException, ParseException {
		if (!validator.validate(startDate, endDate)) {
			LOGGER.error(Messages.getString("CoinbaseService.error.message10")); //$NON-NLS-1$
			throw new DataValidationException(Messages.getString("CoinbaseService.error.message10")); //$NON-NLS-1$
		}

		return this.dao.getBitcoinMovingAverage(utils.convertToDate(startDate), utils.convertToDate(endDate));
	}

}
