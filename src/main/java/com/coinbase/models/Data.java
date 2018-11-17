package com.coinbase.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	@JsonProperty("currency")
	private String currency;

	@JsonProperty("base")
	private String base;

	@JsonProperty("prices")
	private ArrayList<Price> prices;

	private ArrayList<Float> priceList;

	public Data(String base, String currency, ArrayList<Price> prices, ArrayList<Float> priceList) {
		this.base = base;
		this.currency = currency;
		this.prices = prices;
		this.priceList = priceList;
	}

	public ArrayList<Float> getPriceList() {
		return priceList;
	}

	public void setPriceList(ArrayList<Float> priceList) {
		this.priceList = priceList;
	}

	public Data() {
		super();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public ArrayList<Price> getPrices() {
		return prices;
	}

	public void setPrices(ArrayList<Price> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "Data [currency=" + currency + ", prices=" + prices + "]";
	}
}
