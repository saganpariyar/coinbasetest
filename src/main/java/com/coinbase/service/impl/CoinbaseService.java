package com.coinbase.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinbase.Job;
import com.coinbase.dao.AppDao;
import com.coinbase.dao.impl.CoinbaseDao;
import com.coinbase.models.BTCData;
import com.coinbase.models.Data;
import com.coinbase.models.Price;
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

	@Override
	public void getPriceByDates(String startDate, String endDate, int bucket)
			throws DataValidationException, ParseException {
		Date endDateValue  = utils.convertToDate(endDate);
		Date startDateValue  = utils.convertToDate(startDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDateValue);
		cal.add(Calendar.DAY_OF_MONTH, bucket - 1);
		Date tempEndDateValue = cal.getTime();
		
 		BTCData data =  this.dao.getPriceByDates(startDateValue, endDateValue);
 		BTCData dataResponse = new BTCData(new Data());
 		 ArrayList<Price> prices = new ArrayList<Price>();
 		while(startDateValue.compareTo(endDateValue) <=0 ) {
 			System.out.println(startDateValue);
 			BTCData temp = new BTCData();
 			float tempPrice = -1;
 			Price tempPriceObj = new Price();
 			
 			for(Price price: data.getData().getPrices()) {
 				if (price.getTime().compareTo(startDateValue) > 0 && price.getTime().compareTo(tempEndDateValue) <= 0) {
 					if(tempPrice < Float.parseFloat(price.getPrice())) {
 							tempPrice = Float.parseFloat(price.getPrice());
 							tempPriceObj = price;

 					}
 				}
 			}
 			prices.add(tempPriceObj);
 			cal.setTime(tempEndDateValue);
 			cal.add(Calendar.DAY_OF_MONTH, 1);
 			startDateValue = cal.getTime();
 			cal.setTime(startDateValue);
 			cal.add(Calendar.DAY_OF_MONTH, bucket -1 );
 			tempEndDateValue = cal.getTime();
 		}
 		
 		System.out.println(prices);
		
	}
	
	public static void main(String[] args) {
		CoinbaseService service  = new CoinbaseService();
		try {
			service.getPriceByDates("2018-09-01", "2018-09-30",10);
		} catch (DataValidationException | ParseException e) {
			
			e.printStackTrace();
		}
	}

}
