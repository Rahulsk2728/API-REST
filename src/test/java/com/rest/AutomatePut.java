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


public class AutomatePut {

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
	public void validate_put_request_bdd_style() {
		String workspaceID = "/a8a26924-b0ba-4ecc-b5c1-e7d8c35db06a";
		String payload = "{\n"
				+ "    \"workspace\": {\n"
				+ "        \"name\": \"my ThirdWorkspace\",\n"
				+ "        \"type\": \"personal\",\n"
				+ "        \"description\": \"Rest assured created this\"\n"
				+ "    }\n"
				+ "}";
		
		given().
		   body(payload).
		   pathParam("workspaces", workspaceID).
		    
		when().
		   put("/workspaces/" + workspaceID).
		   
		 then().
		     log().all().
		       body("workspace.name", equalTo("myThirdWorkspace"),
		    		   "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
		    		   "workspace.id", equalTo(workspaceID));
  }

	
	}
	
