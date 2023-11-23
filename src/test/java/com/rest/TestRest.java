package com.rest;

import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestRest {

//	@Test
	public void test() {
		given().
		   baseUri("https://api.postman.com").header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
		when().
		   get("/workspaces").
		then().
		  log().all().
		  assertThat().
		  statusCode(200).
		 body("workspaces.name", hasItems("My Workspace"), "workspaces.type", hasItems("personal"),
				"workspaces[0].name", equalTo("My Workspace")
				        );
	
	}
	
//	@Test
public void extract_response() {
		
		Response res =given().
		   baseUri("https://api.postman.com").header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
		when().
		   get("/workspaces").
		then().
		  log().all().
		  assertThat().
		  statusCode(200).
		  extract().
		  response();
		System.out.println("Response" +  res.asString());
		
	}	
		
// 	@Test
public void extract_single_value_from_response() {
	   Response res = given().
			   baseUri("https://api.postman.com").
			   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
		when().
		       get("/workspaces").
		  then().
		      assertThat().
		      statusCode(200).
		      extract().
		      response();
	   JsonPath jsonPath = new JsonPath(res.asString());
	  System.out.println("workspace name =" + jsonPath.getString("workspaces[0].name"));
	 
  }	
 	
// 	@Test
 	public void extract_single_value_from_response_second() {
 	  String res = given().
 			   baseUri("https://api.postman.com").
 			   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
 		when().
 		       get("/workspaces").
 		  then().
 		      assertThat().
 		      statusCode(200).
 		      extract().
 		      response().asString();
 	  System.out.println("Response is " + JsonPath.from(res).getString("workspaces[0].name"));
 	 
   }	
 	
// 	@Test
 	public void hamcrest_assert_on_extracted_response() {
 		 String name = given().
 	 			   baseUri("https://api.postman.com").
 	 			   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
 	 		when().
 	 		       get("/workspaces").
 	 		  then().
 	 		      assertThat().
 	 		      statusCode(200).
 	 		      extract().
 	 		      response().path("workspaces[0].name");
 	 	  System.out.println("Response is " + name);
 	 	  
 	 	  assertThat(name, equalTo("My Workspace"));
 	}
 	
// 	@Test
 	public void validate_response_body_hamcrest_learnings() {
 		given().
		   baseUri("https://api.postman.com").
		   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
	when().
	       get("/workspaces").
	  then().
	      assertThat().
	      statusCode(200).
	      body("workspaces.name" , contains("My Workspace"),
	    		  "workspaces.name", is(not(emptyArray())),
	    		  "workspaces.name", hasSize(1),
	    		  "workspaces[0]", hasKey("id")
	    		  
	    		  );
 	}
 	
// 	@Test
 	public void  request_response_logging() {
 		given().
		   baseUri("https://api.postman.com").
		   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
		   log().all().
	when().
	       get("/workspaces").
	  then().
	      assertThat().
	      statusCode(200).
	      log().all();
 		}
 	
// 	@Test
 	public void  log_only_if_error() {
 		given().
		   baseUri("https://api.postman.com").
		   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
		   log().all().
	when().
	       get("/workspaces").
	  then().
	      log().ifError().
	      assertThat().
	      statusCode(200);
	   
 		}
// 	@Test
	public void  log_only_if_validation_fails() {
 		given().
		   baseUri("https://api.postman.com").
		   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
		   config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
		   log().all().
	when().
	       get("/workspaces").
	  then().
	      log().ifValidationFails().
	      assertThat().
	      statusCode(201);
	   
 		}
// 	@Test
 	public void  logs_blacklist_headers() {
 		Set<String> headers = new HashSet<String>();
 		headers.add("X-Api-Key");
 		given().
		   baseUri("https://api.postman.com").
		   header("X-Api-Key", "PMAK-652d2660a2872d003185c0f3-cdddaa438d8e514e9bd01906f11d2a5162").
		   config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key"))).
		   log().all().
	when().
	       get("/workspaces").
	  then().
	      log().ifValidationFails().
	      assertThat().
	      statusCode(201);
 		}
 	
// 	@Test
 	public void  multiple_headers() {
 		Header header = new Header("header","value2");
 		Header matchHeader = new Header("x-mock-match-request-headers","header");
 		
 		Headers headers = new Headers(header,matchHeader);
 		given().
		   baseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io").
		   headers(headers).	
		  
	when().
	       get("/get").
	  then().
	      log().all().
	      assertThat().
	      statusCode(200);
 		}	
 	
// 	@Test
 	public void  multipleheaders_using_hashmap() {
 		HashMap<String ,String> headers = new HashMap<String, String>();
 		headers.put("header","value2");
 		headers.put("x-mock-match-request-headers","header");

 		given().
		   baseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io").
		   headers(headers).	
		  
	when().
	       get("/get").
	  then().
	      log().all().
	      assertThat().
	      statusCode(200);
 		}	
 	
//	@Test
 	public void  multivalue_header_in_the_request() {
 		Header header1 = new Header("multiValueHeader","value1");
 		Header header2 = new Header("multivalueHeader","value2");
 		
 		Headers headers = new Headers(header1,header2);
 		given().
		   baseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io").
//		   headers(headers).	
		   headers(headers).
		  log().all().
	when().
	       get("/get").
	  then().
	      log().all().
	      assertThat().
	      statusCode(200);
 		}	
	
// 	@Test
	public void  assert_response_header() {
 		HashMap<String ,String> headers = new HashMap<String, String>();
 		headers.put("header","value2");
 		headers.put("x-mock-match-request-headers","header");
 		
 		
 		given().
		   baseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io").
		   headers(headers).
		  log().all().
	when().
	       get("/get").
	  then().
	      assertThat().
	      statusCode(200).extract().headers();
 		
 		}	
 	
 	
// 	@Test
	public void  extract_response_header() {
 		HashMap<String ,String> headers = new HashMap<String, String>();
 		headers.put("header","value2");
 		headers.put("x-mock-match-request-headers","header");
 		
 		Headers extractedResponseHeader = given().
		   baseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io").  
		   headers(headers).
		
	when().
	       get("/get").
	  then().
	      assertThat().
	      statusCode(200).
	      header("responseValue2","resValue2").extract().headers();
 		 
 		 for(Header header : extractedResponseHeader) {
 		 System.out.println("The extracted Name is " + header.getName() + "Header value " + header.getValue());
 		 }
 		
 		System.out.println("Response extracted header name is " + extractedResponseHeader.get("responseValue2").getName());
 		System.out.println("Response extracted header value is " + extractedResponseHeader.get("responseValue2").getValue());
 		
 		
 		
 		}	

 	
 	@Test
	public void  extract_multivalue_response_header() {
 		HashMap<String ,String> headers = new HashMap<String, String>();
 		headers.put("header","value2");
 		headers.put("x-mock-match-request-headers","header");
 		
 	 Headers extractedResponse = given().
		   baseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io").  
		   headers(headers).
		
	when().
	       get("/get").
	  then().
	     log().all().
	      assertThat().
	      statusCode(200).
	      extract(). 
	      headers();
 	 
 	 List<String> values = extractedResponse.getValues("multiValues");
 	   
 	    for (String value : values) {
 	    	System.out.println("The values are " + value);
 	    } 
     }
}
