package com.coinbase.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinbase.Job;
import com.coinbase.constants.Constants;

public class Validator {

	Utility utils = new Utility();
	final Logger LOGGER = LoggerFactory.getLogger(Job.class);

	public boolean validate(String query) {
		for (types type : types.values()) {
			if (type.name().equalsIgnoreCase(query))
				return true;
		}
		return false;
	}

	public boolean validate(String startDate, String endDate) {

		try {

			utils.convertToDate(startDate);
			utils.convertToDate(endDate);

		} catch (ParseException e) {

			return false;
		}

		return true;
	}

	public boolean dataHoldingValid(String lastDate) {
		DateFormat df = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);

		return lastDate != null && lastDate.equals(df.format(new Date()));
	}

	public boolean validateDays(String days) {
		try {
			Integer.parseInt(days);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean validateDaysForDecision(String days) {

		return Integer.parseInt(days) > 10;
	}
}
