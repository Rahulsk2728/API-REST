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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class RequestParameters {

	//To send single query parameter
	@Test
	public void single_query_parameter() {
		given().
		     baseUri("https://postman-echo.com").
		     param("foo1","bar1").
		when().
		     get("/get").
		 then().
		    log().all().
		    assertThat().
		    statusCode(200);
		
	}
	
	//To send multiple query parameter
	@Test
	public void multiple_query_parameter() {
		
		HashMap<String, String> queryParams = new HashMap<String , String> ();
 		queryParams.put("foo1", "bar1");
 		queryParams.put("foo2", "bar2");
		
		given().
		     baseUri("https://postman-echo.com").
//		     queryParam("foo1","bar1").
//		     queryParam("foo2","bar2").
		     queryParams(queryParams).
		     
		when().
		     get("/get").
		 then().
		    log().all().
		    assertThat().
		    statusCode(200);
		
	}
	
	//To send multiple query parameter
	@Test
	public void multiplevalue_query_parameter() {
		
		given().
		     baseUri("https://postman-echo.com").
            queryParam("foo1","bar1","bar2","bar3").
		     
		when().
		     get("/get").
		 then().
		    log().all().
		    assertThat().
		    statusCode(200);
		
	}
	
	//To send pathparameter
		@Test
		public void path_parameter() {
			
			given().
			     baseUri("https://reqres.in").
	           pathParam("userId","2").
	           pathParam("bookId","1").
	           log().all(). 
			when().
			     get("/get/users{userId}/{bookId}").
			 then().
			    log().all().
			    assertThat().
			    statusCode(200);
			
		}
	
		@Test
		public void form_urlencoded() {
			
			given().
			     baseUri("https://reqres.in").
			     config(config().encoderConfig(EncoderConfig.encoderConfig()
			    		 .appendDefaultContentCharsetToContentTypeIfUndefined(false))).
	         formParam("key1", "value1").
	         formParam("key2", "value 2").
	           log().all(). 
			when().
			     get("/get/users{userId}/{bookId}").
			 then().
			    log().all().
			    assertThat().
			    statusCode(200);
			
		}
	
}
