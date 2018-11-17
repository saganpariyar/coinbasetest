package com.coinbase.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {

	@JsonProperty("price")
	String price;

	@JsonProperty
	Date time;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Price [price=" + price + ", time=" + time + "]";
	}

}
