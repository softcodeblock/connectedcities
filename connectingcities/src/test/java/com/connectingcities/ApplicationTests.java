package com.connectingcities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest

class ApplicationTests {
	
	@Autowired
    MockMvc mockmvc;
	
	@Autowired
	private Environment env;
	
	// Success case, when both cities are connected and found 
	@Test
	void TestConnectingCitiesFound() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=dallas&destination=atlanta").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = "yes";
		assertEquals(expected, result.toString());
	}
	
	// Success case, change origin city as destination city and destination city as origin. Test that cities are connected or not
	@Test
	void TestConnectingCitiesAfterSwaping() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=dallas&destination=atlanta").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = "yes";
		assertEquals(expected, result.toString());
	}
	
	// Fail case, When one/both of the cities do not have any connection.
	@Test
	void TestConnectingCitiesNotFound() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=dallas&destination=Irving").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = "no";
		assertEquals(expected, result.toString());
	}
	
	// Success case, When user enter case sensitive data for origin city and destination city.
	@Test
	void TestConnectingCitiesNotFoundCaseSensitive() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=DALLAS&destination=ATLANTA").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = "yes";
		assertEquals(expected, result.toString());
	}
	
	// Fail case, When one of the request parameter is missing either origin city or destination city.
	@Test
	void TestConnectingCitiesWithOneValueMissing() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=dallas&destination=").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = "no";
		assertEquals(expected, result.toString());
	}
	
	// Fail case, When both request parameter is missing, origin city and destination city.
	@Test
	void TestConnectingCitiesWithNoValue() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=&destination=").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = "no";
		assertEquals(expected, result.toString());
	}
	
	// Fail case, When one of the request parameter contains some special symbol either origin city or destination city.
	@Test
	void TestConnectingCitiesWithSpecialSymbol() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=@@&destination=##").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = "no";
		assertEquals(expected, result.toString());
	}
	
	// Fail case, When one of the request parameter name is incorrect either origin city parameter name or destination city parameter name.
	@Test
	void TestConnectingCitiesWithIncorrectParametername() throws Exception {
		
		MvcResult MVCR = mockmvc.perform(
				MockMvcRequestBuilders.get("/connected?origin=&destination1=").accept(org.springframework.http.MediaType.APPLICATION_JSON)
				).andReturn();
		String result = MVCR.getResponse().getContentAsString();
		String expected = env.getProperty("Test.ConnectingCitiesWithIncorrectParameternameMsg");
		assertEquals(expected, result.toString());
	}

}
