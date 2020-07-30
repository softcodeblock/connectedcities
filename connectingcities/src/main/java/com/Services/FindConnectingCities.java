package com.Services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class FindConnectingCities {
	Logger logger = LoggerFactory.getLogger(FindConnectingCities.class);
	
	public String isConnectionFind(String city1, String city2, Map<String, String> hm1) {
		logger.info("FindConnectingCities: isConnectionFind");
		if(hm1.get(city1.toLowerCase().trim()).equals(city2.toLowerCase().trim())) 
			return "yes";
		return "no";
	}
}
