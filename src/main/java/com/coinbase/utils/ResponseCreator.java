package com.coinbase.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.coinbase.models.BTCData;

public class ResponseCreator {

	public ResponseEntity<Object> createErrorResponse(String message) {
		return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Object> createErrorResponse(String message, String desc) {
		return new ResponseEntity<>(new ErrorResponse(message, desc), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Object> createSuccessResponse(BTCData btcData) {
		String data = btcData.toString();
		System.out.println("to string " + data);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	public ResponseEntity<Object> createNotFoundResponse(String message) {
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Object> createSuccessResponseMovingAvg(float bitcoinMovingAverage) {

		return new ResponseEntity<>("{'moving average: " + bitcoinMovingAverage + " '}", HttpStatus.OK);
	}

	public ResponseEntity<Object> createSuccessResponseTradeDecision(String tradeDecision) {
		return new ResponseEntity<>("{'Trade decision : " + tradeDecision + " '}", HttpStatus.OK);
	}
}
