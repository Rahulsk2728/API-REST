package com.rest;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
public class JSONAPI_JSONArray {

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
			public void  serialize_json_array_using_jackson() throws JsonProcessingException {
				
			   ObjectMapper objectMapper = new ObjectMapper();
				ArrayNode arrayNodeList  = objectMapper.createArrayNode(); 
			   
				ObjectNode obj5001Node = objectMapper.createObjectNode();
				obj5001Node.put("id", "5001");
				obj5001Node.put("type", "None");
				
				ObjectNode obj5002Node = objectMapper.createObjectNode();
				obj5002Node.put("id", "5002");
				obj5002Node.put("type", "Glazed");
			   
				arrayNodeList.add(obj5001Node);
				arrayNodeList.add(obj5002Node);
				
				String jsonListStr = objectMapper.writeValueAsString(arrayNodeList);
				
				
				given().
				       body(jsonListStr). 
				 when(). 
				        post("/post"). 
				  then().spec(responseSpecification).      
				 assertThat(). 
				     body("msg", equalTo ("success") );
							 
			}   
}
