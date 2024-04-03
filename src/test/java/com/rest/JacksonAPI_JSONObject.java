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


public class JacksonAPI_JSONObject {

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
	public void validate_post_request_aspost_map() throws JsonProcessingException {
		 
		HashMap<String , Object > mainObject = new HashMap<String , Object >();
		
		HashMap<String , Object > nestedObject = new HashMap<String , Object >();
		nestedObject.put("name", "myThirdWorkspace");
		nestedObject.put("type", "personnel");
		nestedObject.put("description", "Rest Assured  created this ");
		
		mainObject.put("workspace", nestedObject);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String mainObjectStr = objectMapper.writeValueAsString(mainObject);
		
		given().
		       body(mainObject). 
		 when(). 
		        post("/workspaces"). 
		  then().spec(responseSpecification).      
		 assertThat(). 
		     body("workspace.name", equalTo ("myThirdWorkspace") , 
					    "workspace.id", matchesPattern("^[a-z0-9-]{36}$") );
		
		
	}
	
}
