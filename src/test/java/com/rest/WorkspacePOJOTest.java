package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.rest.workspace.Workspace;
import com.pojo.rest.workspace.WorkspaceRoot;
import com.rest.pojo.simple.SimplePojo;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class WorkspacePOJOTest {

	
	@BeforeClass
    public void beforeClass() {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
		      setBaseUri("https://35bbec25-c41c-4957-9eed-85c5c354b133.mock.pstmn.io").
		        setContentType(ContentType.JSON).
                log(LogDetail.ALL);
	   
		RestAssured.requestSpecification = requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder(). 
				expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		   responseSpecification = responseSpecBuilder.build();
	
	}
	
	
	@Test( dataProvider = "workspace")
	public void validate_post_request_payload_as_map(String name , String type, String description) {
		
		Workspace workspace = new Workspace(name , type , description);
		WorkspaceRoot workspaceroot = new WorkspaceRoot(workspace);
		
		
		 WorkspaceRoot deserializedWorkspaceRoot = given().
                body(workspaceroot). 
          when(). 
                post("/workspaces").
          then().spec(responseSpecification). 
               extract().
                response(). 
                as(WorkspaceRoot.class);
		 
		 
               assertThat(deserializedWorkspaceRoot.getWorkspace().getName(),
                    equalTo(workspaceroot.getWorkspace().getName()));              
	}
	
   @DataProvider(name = "workspace")	
	public Object[][] getWorkspace() {
	   return new Object[][] { 
			   {"myWorkspace", "personal" , "description"},
	           { "myWorkspace", "team", "description" }
       };
    }

}
