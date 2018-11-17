package com.coinbase.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coinbase.Job;
import com.coinbase.service.AppService;
import com.coinbase.service.impl.CoinbaseService;
import com.coinbase.utils.DataValidationException;
import com.coinbase.utils.ResponseCreator;

@RestController
@RequestMapping("/v1/api/bitcoin")
public class AppController {

	final Logger LOGGER = LoggerFactory.getLogger(Job.class);

	AppService service = new CoinbaseService();

	ResponseCreator responseCreator = new ResponseCreator();

	@RequestMapping("/get_price")
	ResponseEntity<Object> getBitcoinPrice(@RequestParam(value = "start_date", required = false) String startDate,
			@RequestParam(value = "end_date", required = false) String endDate,
			@RequestParam(value = "type", required = false) String type) {
		System.err.println(startDate);
		LOGGER.info("get_price by type <type> or custom dates <start_date> and <end_date>");
		try {
			if (type != null) {
				LOGGER.info("Getting Bitcoin prices for " + type);
				return responseCreator.createSuccessResponse(this.service.getPriceByType(type));
			} else if (startDate != null && endDate != null) {
				LOGGER.info("Getting Bitcoin prices for dates " + startDate + " to " + endDate);
				return responseCreator.createSuccessResponse(this.service.getPriceByDates(startDate, endDate));
			} else {
				LOGGER.error(
						"Invalid query params, get_price by type <type> or custom dates <start_date> and <end_date>");
				return responseCreator.createNotFoundResponse(
						"Invalid query params, get_price by type <type> or custom dates <start_date> and <end_date>");
			}
		} catch (DataValidationException e) {
			LOGGER.error("Validation Exception " + e.getMessage());
			return responseCreator.createErrorResponse(e.getMessage(), e.getDesc());
		} catch (ParseException e) {
			LOGGER.error("Data parsing  Exception " + e.getMessage());
			return responseCreator.createErrorResponse(e.getMessage());
		}
	}

	@RequestMapping("/get_moving_avg")
	ResponseEntity<Object> getBitcoinMovingAverage(
			@RequestParam(value = "start_date", required = false) String startDate,
			@RequestParam(value = "end_date", required = false) String endDate,
			@RequestParam(value = "days", required = false) String days) {
		LOGGER.info("get_moving_avg by X days <days> or custom dates <start_date> and <end_date>");
		try {
			if (days != null) {
				LOGGER.info("Getting Moving average of Bitcoin price for " + days + " days ");
				return responseCreator.createSuccessResponseMovingAvg(this.service.getBitcoinMovingAverage(days));
			} else if (startDate != null && endDate != null) {
				LOGGER.info("Getting Moving average of Bitcoin price for dates " + startDate + " to  " + endDate);
				return responseCreator
						.createSuccessResponseMovingAvg(this.service.getBitcoinMovingAverage(startDate, endDate));
			} else {
				LOGGER.error(
						"Invalid query params, get_moving_avg by X days <days> or custom dates <start_date> and <end_date>");
				return responseCreator.createNotFoundResponse(
						"Invalid query params, get_moving_avg by X days <days> or custom dates <start_date> and <end_date>");
			}
		} catch (DataValidationException e) {
			LOGGER.error("Validation Exception " + e.getMessage());
			return responseCreator.createErrorResponse(e.getMessage(), e.getDesc());
		} catch (ParseException e) {
			LOGGER.error("Data parsing  Exception " + e.getMessage());
			return responseCreator.createErrorResponse(e.getMessage());
		}

	}

	@RequestMapping("/get_trade_decision")
	ResponseEntity<Object> getTradeDecision(@RequestParam("days") String days) {
		LOGGER.info("get_trade_decision by X days moving average <days>");
		try {
			if (days != null) {
				LOGGER.info("Getting Trading decission by looking at Bitcoin proces for " + days + " days ");
				return responseCreator.createSuccessResponseTradeDecision(this.service.getTradeDecision(days));
			} else {
				LOGGER.error("Invalid query params, get_trade_decision by X days moving average <days>");
				return responseCreator.createNotFoundResponse(
						"Invalid query params, get_trade_decision by X days moving average <days>");
			}
		} catch (DataValidationException e) {
			LOGGER.error("Validation Exception " + e.getMessage());
			return responseCreator.createErrorResponse(e.getMessage(), e.getDesc());
		}

	}

}
