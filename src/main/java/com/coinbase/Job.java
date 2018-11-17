package com.coinbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Job {

	public static void main(String[] args) {

		final Logger LOGGER = LoggerFactory.getLogger(Job.class);
		LOGGER.info("started Application"); 
		SpringApplication.run(Job.class, args);
		LOGGER.info("Thanks for using Application");
	}
}
