package com.rest;

import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;


public class MyClass {
	
	@Test
	 public void validate_status_code() {
		
		RequestSpecification requestspecification = given().
		      baseUri("https://api.postman.com").
		      header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162");
	
		 given().spec( requestspecification).
		when().
		      get("/workspaces").
		then().
		      log().all().
		     assertThat().
		     statusCode(200);
	 }
	
	@Test
	public void validate_response_body() {
		given().
	      baseUri("https://api.postman.com").
	      header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162");
	when().
	      get("/workspaces").
	then().
	      log().all().
	     assertThat().
	     statusCode(200)
	     .body("workspacesname[0]", equalTo("Team Workspace"));
	}
	
	

}
