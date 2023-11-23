package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
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

public class RequestSpecificationExample {
	
	
	@BeforeClass
     public void beforeClass() {
//		requestspecification = with().
//				   baseUri("https://api.postman.com").
//				   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
//	               log().all();
//		This is requestspec
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder .setBaseUri("https://api.postman.com");
		requestSpecBuilder .addHeader("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162");
	   RestAssured.requestSpecification = requestSpecBuilder.build();
	
	}
	
	@Test
	public void query_Testcase() {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
		System.out.println(queryableRequestSpecification.getBaseUri());
		System.out.println(queryableRequestSpecification.getHeaders());
	}
	
//	@Test
	public void validate_status_code() {
		   Response response = get("/workspaces").then().log().all().extract().response();
		   assertThat(response.statusCode() , is(equalTo(200)));
	}
	
//	@Test
	public void validate_response_body() {
		   Response response = given().spec(requestSpecification).get("/workspaces").then().log().all().extract().response();
		   assertThat(response.statusCode() , is(equalTo(200)));
		   assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));
     	}	
}
