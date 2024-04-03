package com.rest;

import org.testng.annotations.BeforeClass;


import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.simple.SimplePojo;

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

public class SimplePOJOTest {

	



//@Test
public void simple_pojo_example_serialization() {
	
	SimplePojo simplePojo = new SimplePojo("value1", "value2");
	
	String payload = "{\n"
			+ "    \"key1\": \"value1\",\n"
			+ "    \"key2\": \"value2\"\n"
			+ "}";
	
	given().
	     body(simplePojo). 
	when(). 
	      post("/postSimpleJson").
    then().spec(responseSpecification).	
    assertThat(). 
          body("key1", equalTo(simplePojo.getkey1()), 
        		   "key2", equalTo(simplePojo.getkey2()));
   }
    
@Test
public void example_pojo_desarialization() throws JsonProcessingException {
	
	SimplePojo simplePojo = new SimplePojo("value1", "value2");
	
	
	SimplePojo desrailizedPojo =given().
	     body(simplePojo). 
	when(). 
	      post("/postSimpleJson").
    then().spec(responseSpecification).	
      extract(). 
      response().
       as(SimplePojo.class);   
	
	ObjectMapper objectMapper = new ObjectMapper();
	String deserializedPojoStr = objectMapper.writeValueAsString(desrailizedPojo);
	String simplePojoStr = objectMapper.writeValueAsString(simplePojo);
    assertThat(objectMapper.readTree(deserializedPojoStr) , equalTo(objectMapper.readTree(simplePojoStr)));
  }



}