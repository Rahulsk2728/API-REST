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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class RequestPayLoadComplexJSON {
	io.restassured.specification.ResponseSpecification customResponseSpecification;
	
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
				expectStatusCode(200).
				expectContentType(ContentType.JSON).
				log(LogDetail.ALL);
		
		   customResponseSpecification = responseSpecBuilder.build();	
	}
	
	@Test
	public void validate_post_request_payload_complexjson_array() {
		
		
		//Hashmap for batter JSON
		   List<Integer> idArrayList = new ArrayList<Integer>();
	        idArrayList.add(5);
	        idArrayList.add(9);
		
		
	        HashMap<String, Object> batterHashMap1 = new HashMap<String,Object>();
	        batterHashMap1.put("id", idArrayList);
	        batterHashMap1 .put("type", "Chocolate");
	        
	        HashMap<String, Object> batterHashMap2 = new HashMap<String,Object>();
	        batterHashMap2.put("id", "101");
	        batterHashMap2 .put("type", "Regular");
	        
	        List<HashMap<String, Object>> batterArrayList = new ArrayList<HashMap<String, Object>>();
	       batterArrayList.add(batterHashMap1);
	       batterArrayList.add(batterHashMap2);
	        
	       HashMap<String, List <HashMap<String, Object>>> batterHashMap = new   HashMap<String, List <HashMap<String, Object>>> ();
	       batterHashMap.put("batter", batterArrayList);
	       
	       
	       //HashMap for topping JSON
	       List<String> idArrayList1 = new ArrayList<String>();
	        idArrayList1.add("test1");
	        idArrayList1.add("test2");
	        
	        HashMap<String, Object> topingHashMap = new HashMap<String,Object>();
	        topingHashMap.put("id", "5002");
	        topingHashMap .put("type", idArrayList1);
	       
	        HashMap<String, Object> topingHashMap2 = new HashMap<String,Object>();
	        topingHashMap2.put("id", "5001");
	        topingHashMap2 .put("type", "None");
	        
	        List<HashMap<String, Object>> toppingrArrayList = new ArrayList<HashMap<String, Object>>();
	        toppingrArrayList.add(topingHashMap);
	        toppingrArrayList.add(topingHashMap2);
	        
	        
	        //Main HashMap for both batter and topping
	        
	        HashMap<String, Object> mainHashMap = new HashMap<String,Object>();
	        mainHashMap.put("id", "101");
	        mainHashMap .put("type", "donut");
	        mainHashMap.put("name", "Cake");
	        mainHashMap .put("ppu", 0.55);
	        mainHashMap.put("batters",batterHashMap);
	        mainHashMap .put("topping", toppingrArrayList);
	        
	        
		given().
		   body(mainHashMap).
		
		when().
		    post("/postComplexJson").
		    
		then().spec(customResponseSpecification).
		   assertThat().
		   body("msg",equalTo("Success"));		
		
		
	}
	
	 
}