package com.rest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;


public class ResponseSpecification {
	io.restassured.specification.ResponseSpecification responseSpecification;
	
	@BeforeClass
    public void beforeClass() {
//		requestspecification = with().
//				   baseUri("https://api.postman.com").
//				   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
//	               log().all();
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder .setBaseUri("https://api.postman.com");
		requestSpecBuilder .addHeader("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162");
	   
		RestAssured.requestSpecification = requestSpecBuilder.build();
	
//		responseSpecification = RestAssured.expect().
//				statusCode(200).
//				contentType(ContentType.JSON).log().all();
//		
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder(). 
				expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		   responseSpecification = responseSpecBuilder.build();
	
		
	}
	@Test
	public void validate_StatusCode() {
	get("/workspaces");
	
		
	}
	
	@Test
	public void validate_responsebody() {
		Response response = get("/workspaces").
				 then().
				    extract().response();
				assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));
			}
	}
	
	
	

