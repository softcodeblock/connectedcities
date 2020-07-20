package com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class testclass {

	public static void main(String[] args) throws IOException {
		Stream<String> row1 = Files.lines(Paths.get("src/main/resources/connectedcities.csv"));
		Map<String, String> hm = new HashMap<>();
		Map<String, String> hm1 = new HashMap<>();
		hm = 	row1
				.map(x ->x.split(","))
				.collect(Collectors.toMap(x -> x[0], x -> x[1]));
		
		row1.close();
		
		for(String key : hm.keySet()) {
			hm1.put(key, hm.get(key));
			hm1.put(hm.get(key), key.toString());
		}
	}

}
