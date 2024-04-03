package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
public class UploadFile {

	@Test
	public void upload_File_multipart_formdata() {
	  String attributes = "{\"name\" : \"temp.txt\",\"parent\" : {\"id\":\"123456\"}}";
		
		given().
	     baseUri("https://postman-echo.com").
	     multiPart("file", new File("temp.txt")).
	     multiPart("attribute", attributes, "application/json").
	     log().all().
		
	when().
	    post("/post").
	    
	then().
	log().all().
	   assertThat().
	  statusCode(200);		
	
   }
}	
	