package com.rest;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class JacksonAPI_JSONArray {

	
	@BeforeClass
    public void beforeClass() {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
		      setBaseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io/post").
		      addHeader("x-mock-match-request-body", "true").
//		      setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)
//		    		  )).
		      setContentType("application/json; charset=utf-8").
		       log(LogDetail.ALL);
	   
		RestAssured.requestSpecification = requestSpecBuilder.build();

		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder(). 
				expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		   responseSpecification = responseSpecBuilder.build();
	
		
	}
	@Test
	public void validate_post_request_jsonarray_as_list() throws JsonProcessingException  {
		
		HashMap<String , String > obj5001 = new HashMap<String , String >();
		obj5001.put("id", "5001");
		obj5001.put("type", "none");
		
		HashMap<String , String > obj5002 = new HashMap<String , String >();
		obj5002.put("id", "5002");
		obj5002.put("type", "Glazed");
		
		List<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
		jsonList.add(obj5001);
		jsonList.add(obj5002);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String JsonListStr = objectMapper.writeValueAsString(jsonList);
		
		
		given().
	       body(JsonListStr). 
	 when(). 
	        post("/post"). 
	  then().spec(responseSpecification).      
	 assertThat(). 
	     body("msg", equalTo ("success")) ;
				   
	}
}
