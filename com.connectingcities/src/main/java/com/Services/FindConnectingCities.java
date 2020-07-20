package com.Services;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FindConnectingCities {
	
	public String isConnectionFind(String city1, String city2, Map<String, String> hm1) {
		if(hm1.get(city1.toLowerCase()) != null && hm1.get(city1.toLowerCase().trim()).equals(city2.toLowerCase().trim())) 
			return "yes";
		return "no";
	}
}
