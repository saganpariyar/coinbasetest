package com.coinbase.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.coinbase.constants.Constants;
import com.coinbase.models.BTCData;
import com.coinbase.models.Data;
import com.coinbase.models.Price;

public class Utility {

	public Date convertToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);
		sdf.setLenient(false);
		return sdf.parse(date);
	}

	public Date firstDayOfLastWeek(Calendar cal) {

		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.WEEK_OF_YEAR, -1);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime();
	}

	public Date firstDayOfLastMonth(Calendar cal) {

		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}

	public Date firstDayOfLastYear(Calendar cal) {

		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.YEAR, -1);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}

	public Date lastDayOfLastWeek(Calendar cal) {

		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime();
	}

	public Date lastDayOfLastMonth(Calendar cal) {

		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}

	public Date lastDayOfLastYear(Calendar cal) {

		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.DATE, 1);
		c.set(Calendar.MONTH, 1);
		return c.getTime();
	}

	public Date getDayMinus(Calendar cal, int days) {

		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.DATE, -days);
		return c.getTime();
	}

	public ArrayList<Date> getDatesForType(String typeValue) {
		ArrayList<Date> dates = new ArrayList<Date>();
		types type = types.valueOf(typeValue.toUpperCase());
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		switch (type) {
		case LAST_WEEK:
			dates.add(this.firstDayOfLastWeek(c));
			dates.add(this.lastDayOfLastWeek(c));
			System.out.println(dates.toString());

		case LAST_MONTH:
			dates.add(this.firstDayOfLastMonth(c));
			dates.add(this.lastDayOfLastMonth(c));
		case LAST_YEAR:
			dates.add(this.firstDayOfLastYear(c));
			dates.add(this.lastDayOfLastYear(c));
		}
		return dates;
	}

	public String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			System.out.println(buffer.toString());
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public BTCData filterDataByDates(Date startDate, Date endDate, BTCData btcData) {
		ArrayList<Price> prices = new ArrayList<Price>();
		ArrayList<Float> priceList = new ArrayList<Float>();
		for (Price price : btcData.getData().getPrices()) {
			if (price.getTime().compareTo(startDate) >= 0 && price.getTime().compareTo(endDate) <= 0) {
				prices.add(price);
				priceList.add(Float.parseFloat(price.getPrice()));
			}
		}
		return new BTCData(new Data(btcData.getData().getBase(), btcData.getData().getCurrency(), prices, priceList));
	}

	public ArrayList<Date> getLastXDays(String days) {
		int day = Integer.parseInt(days);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		ArrayList<Date> dates = new ArrayList<Date>();
		dates.add(this.getDayMinus(c, day));
		dates.add(this.getDayMinus(c, 0));

		return dates;
	}

}
