package com.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Services.FindConnectingCities;
import com.util.AppUtil;

@RestController
@Validated
@RequestMapping("/connected")
public class ConnectingCities {
	
	@Autowired
	FindConnectingCities fcc;
	
	@Autowired
	AppUtil util;
	
	@GetMapping
	public ResponseEntity<String> connected(@RequestParam("origin") @NotBlank String city1, @RequestParam("destination") @NotBlank String city2) throws IOException {
		
		HashMap<String, String> connectedCitiesMap = util.LoadConnectedCities();
		
		String isConnectionFound = fcc.isConnectionFind(city1.trim(), city2.trim(), connectedCitiesMap);
		
		//Returning yes if connection find else return false
		
		return ResponseEntity.status(HttpStatus.OK).body(isConnectionFound);

	}
}
