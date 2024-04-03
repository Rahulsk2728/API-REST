package com.rest;

import static io.restassured.RestAssured.given;


import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.rest.collection.Body;
import com.pojo.rest.collection.CollectionBase;
import com.pojo.rest.collection.CollectionRequest;
import com.pojo.rest.collection.CollectionRootBase;
import com.pojo.rest.collection.CollectionRootRequest;
import com.pojo.rest.collection.CollectionRootResponse;
import com.pojo.rest.collection.FolderBase;
import com.pojo.rest.collection.FolderRequest;
import com.pojo.rest.collection.Header;
import com.pojo.rest.collection.Info;
import com.pojo.rest.collection.RequestBase;
import com.pojo.rest.collection.RequestRequest;
import com.pojo.rest.collection.RequestRootBase;
import com.pojo.rest.collection.RequestRootRequest;
import com.pojo.rest.workspace.WorkspaceRoot;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class ComplexPOJOTest {
 io.restassured.specification.ResponseSpecification responseSpecification;
	
	@BeforeClass
    public void beforeClass() {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
				requestSpecBuilder.setBaseUri("https://api.postman.com");
		      requestSpecBuilder.addHeader("X-Api-Key","PMAK-65c9b59227faf600018ee97c-e576ea6f92d31baeee5a34a08cca2cd1fe");
		      requestSpecBuilder.setContentType(ContentType.JSON).
                log(LogDetail.ALL);
	   
		RestAssured.requestSpecification = requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder(). 
				expectStatusCode(202).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		   responseSpecification = responseSpecBuilder.build();
	
	}

	
	@Test
	public void complex_pojo_create_collection() throws JsonProcessingException { 
		Header header = new Header("Content-Type", "application/json");
		List<Header> headerlist = new ArrayList<Header>();
		headerlist.add(header);
		
		Body body = new Body("raw","{\"data\":\"123\"}");
		
		RequestRequest request = new RequestRequest( "https://postman-echo.com/post","POST", headerlist, body , "This is sample POST request");
		
		RequestRootRequest requestroot = new RequestRootRequest("Sample POJO Request", request);
		List<RequestRootRequest > requestRootList = new ArrayList<RequestRootRequest>();
		requestRootList.add(requestroot);
		
		FolderRequest folder = new FolderRequest("This is a folder", requestRootList);
		List<Object> folderList = new ArrayList<Object>();
		folderList.add(folder);
		
		Info info = new Info( "Sample collection1", "This is just a sample collection","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
		CollectionRequest collection = new CollectionRequest(info , folderList);
		CollectionRootRequest collectionRoot = new CollectionRootRequest(collection);
		
		String collectionUid =given().
             body(collectionRoot). 
  when(). 
            post("/collections").
  then().spec(responseSpecification). 
       extract().
        response(). 
      path("collection.uid");
		
		CollectionRootResponse desrializedCollectionRoot = given().
           pathParam("collectionUid" , collectionUid).		
		 when(). 
		       get("/collections/{collectionUid}").
           then().spec(responseSpecification).
               extract().	
               response().
              as(CollectionRootResponse.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String collectionRootStr = objectMapper.writeValueAsString(collectionRoot);
		String desrializedCollectionRootStr = objectMapper.writeValueAsString(desrializedCollectionRoot);
		
//		JSONAssert.assertEquals(collectionRootStr, desrializedCollectionRootStr , 
//				new CustomComparator(JSONCompareMode.STRICT_ORDER, 
//						new Customization("collection.item[*].item[*].request.url",(o1,o2 ) ) {
//					return true;
//				})
//				
				
//		List<String> urlRequestList;
//		List<String> urlResponseList;
//		
//		for (RequestRootRequest requestRootRequest:requestRootList) {
//			urlRequestList.add(requestRootRequest.getRequest().getHeader());
//		}
//		
	
	}
	
}
