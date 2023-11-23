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

public class AutomateDelete {
	
	public void beforeClass() {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
		setBaseUri("https://api.postman.com").
		addHeader("X-Api-Key", "PMAK-6548b5646e4ced002aa3d5ed-69050d49161af681b6c566efb2106b9e68").
		setContentType(ContentType.JSON).
		log(LogDetail.ALL);
	   RestAssured.requestSpecification = requestSpecBuilder.build();
	
	   ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
			   expectStatusCode(200).
			   expectContentType(ContentType.JSON).
			   log(LogDetail.ALL);
	   RestAssured.responseSpecification = responseSpecBuilder.build();		   
	   
	}
	
	@Test
	public void validate_delete_request_bdd_style() {
		String workspaceID = "/cd8832a4-4683-a2ee-97a3f17d2ee7";
		
		
		given().
		   pathParam("workspace", workspaceID).
		    
		when().
		   delete("/workspaces/{workspaceID}").
		   
		 then().
		     log().all().
		       body(
		    		   "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
		    		   "workspace.id", equalTo(workspaceID));
  
	}
	
	
	
}
