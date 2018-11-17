package com.coinbase.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@JsonProperty("error")
	private String error;

	@JsonProperty("error_description")
	private String errorDescription;

	public ErrorResponse() {

	}

	public ErrorResponse(String error) {
		super();
		this.error = error;
	}

	public ErrorResponse(String error, String errorDescription) {
		super();
		this.error = error;
		this.errorDescription = errorDescription;
	}

	public String getError() {
		return error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

}