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

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;


public class AutomatePost {
	
	
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
	   
//	   @Test
	   public void validate_post_request_bdd_style() {
		   String payload = "{\n"
		   		+ "    \"workspace\": \n"
		   		+ "        {\n"
		   		+ "            \"name\": \"My Workspace\",\n"
		   		+ "            \"type\": \"personal\",\n"
		   		+ "            \"description\":\"Rest assured created this\"\n"
		   		+ "        }\n"
		   		+ "}";
		   
		   
		   with().
		      body(payload);   
		   
		
	     when().
	         post("/workspaces").
	      then().
	        log().all().
	       assertThat().body("workspace.name", equalTo("My Workspace"),
	    		       "workspaces.id", matchesPattern("^[a-z0-9-]{36}$"));
	          
	   }
	   
//	   @Test
	   public void validate_post_request_nonbdd_style() {
		   String payload = "{\n"
		   		+ "    \"workspace\": \n"
		   		+ "        {\n"
		   		+ "            \"name\": \"MyFirstWorkspace2\",\n"
		   		+ "            \"type\": \"personal\",\n"
		   		+ "            \"description\":\"Rest assured created this\"\n"
		   		+ "        }\n"
		   		+ "}";
		   
		    Response  response = given().
		         body(payload).
		         post("/workspaces");
		     assertThat(response.<String>path("workspace.name"), equalTo("myFirstWorkspace"));
		     assertThat(response.<String>path("workspace.id"), matchesPattern("^[a-z0-9-]{36}$"));
	   }
	   
//	   @Test
	   public void validate_post_request_payload_fromfile() {
		  File file = new File("src/main/resources/CreateWorkSpacePayload");
		   
		 given().
		         body(file).
		 when().
		          post("/workspaces"). 
		 then(). 
		           log().all(). 
		           assertThat(). 
		           body( "workspace.name",equalTo("myFirstWorkspace"),
		    		    "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		      
	   }
	   
	   @Test
	   public void validate_post_request_payload_using_as_map() {
		HashMap<String,Object> mainobject = new HashMap<String, Object>();
		   
		HashMap<String,String> nestedobject = new HashMap<String , String>();
		nestedobject.put("name", "myThirdWorkspace");
		nestedobject.put("type", "personal");
		nestedobject.put("description", "RESTAssured created this");
		
		mainobject.put("workspace", nestedobject);
		
		   File file = new File("src/main/resources/CreateWorkSpacePayload");
		   
		 given().log().all().
		         body(mainobject).
		 when().log().all().
		          post("/workspaces"). 
		 then(). 
		           log().all();
//		           assertThat(). 
//		           body( "workspace.name",equalTo("myFirstWorkspace"));
//		    		    "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		      
	   }
	  
	  
	   
	   
	   
	   
	   
	   
	   
	   
    }
