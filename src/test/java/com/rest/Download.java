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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Download {

	@Test
	public void downloadFile() throws IOException {
	
	InputStream is =	given().
	        baseUri("https://raw.githubusercontent.com").
	     log().all().
		
	when().
	    get("/appium/appium/master/sample-code/apps/ApiDemos-debug.apk").
	    
	then().
	log().all().
	extract().
	response().
	  asInputStream();		
	
		OutputStream os = new FileOutputStream(new File ("ApiDemos-debug.apk"));
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		os.write(bytes);
		os.close();
		
   }	
}
