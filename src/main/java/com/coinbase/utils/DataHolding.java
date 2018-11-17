package com.coinbase.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.coinbase.constants.Constants;
import com.coinbase.models.BTCData;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataHolding {
	private static DataHolding myObj;

	private BTCData data;
	private String lastDate;

	static {
		myObj = new DataHolding();
	}

	private DataHolding() {

	}

	public static DataHolding getInstance() {
		return myObj;
	}

	public void updateData(String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		this.data = objectMapper.readValue(data, BTCData.class);

		DateFormat df = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);
		this.lastDate = df.format(new Date());
	}

	public BTCData getData() {
		return this.data;
	}

	public String getLastDate() {
		return this.lastDate;
	}
}
