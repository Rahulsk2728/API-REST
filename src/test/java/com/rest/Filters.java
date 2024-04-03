package com.rest;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class Filters {

	RequestSpecification requestSpecification;
	io.restassured.specification.ResponseSpecification responseSpecification;
	
	
	@BeforeClass
	public void beforeClass() throws FileNotFoundException {
		 PrintStream fileOutputStream = new PrintStream(new File("log.log"));			
		
		 RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder(). 
	          addFilter (new RequestLoggingFilter (fileOutputStream)). 
	          addFilter (new RequestLoggingFilter (fileOutputStream));
		 requestSpecification = requestSpecBuilder.build();
		 
		 ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		 responseSpecification = responseSpecBuilder.build();
	}
	
	@Test
	public void loggingFilter() throws FileNotFoundException {
	
		given(requestSpecification).
		    baseUri("https://postman-echo.com").
		  
//		log().all().
		when().
		    get("/get").
		  then().spec(responseSpecification). 
//		     log().all(). 
		     assertThat(). 
		     statusCode(200);
		
	}
	
}
