package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.Services.FindConnectingCities;

@Component
public class AppUtil {
	
	Logger logger = LoggerFactory.getLogger(AppUtil.class);
	@Cacheable("connected-cities")
	public HashMap<String, String> LoadConnectedCities() throws IOException{
		
		logger.info("AppUtil: LoadConnectedCities : Loading Connected Cities Data");
		//Reads .csv file and and create a stream of data.
		Stream<String> row1 = Files.lines(Paths.get("src/main/resources/connectedcities.csv"));
		
		//Hashmap to hold origin destination connections.
		Map<String, String> hm = new HashMap<>();
		
		//Hashmap to holds both origin destination and destination origin connections.
		Map<String, String> hm1 = new HashMap<>();
		
		// Process data and save data in hashmap in form of key values.
		hm = 	row1
				.map(x ->x.split(","))
				.collect(Collectors.toMap(x -> x[0], x -> x[1]));
		row1.close();
		
		// Destinations can act as origin in cities connection so read hashmap keys as values and values as keys.
		for(String key : hm.keySet()) {
			hm1.put(key.toLowerCase().trim(), hm.get(key).toLowerCase().trim());
			hm1.put(hm.get(key).toLowerCase().trim(), key.toLowerCase().trim());
		}	
		return (HashMap<String, String>) hm1;
	}
}
