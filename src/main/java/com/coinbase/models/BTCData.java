package com.coinbase.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BTCData {

	public BTCData() {
		super();
	}

	@JsonProperty("data")
	private Data data;

	public BTCData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BTCData [data=" + data + "]";
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
