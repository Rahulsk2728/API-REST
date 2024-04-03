package google.oauth;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.Base64;
import java.util.HashMap;
public class GmailAPI {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	String access_token = "ya29.a0AfB_byCF4cG9KJ81Od2tJHOGgubI99SdgjRYw1WbRUCmPErj7I6xPZKXjV5L5TMtaOqhnarGXkrXv38IR2Ewa9SPHMXuxc9s7CvPQ9-ETyLHQ89HNN8BXDhWsv3cTwjcGF-802KcGPAIPahPNwX9lZ25sZefaYBXzwaCgYKAf0SARISFQHGX2MiBxGW98OQb_sJbhWyGtVEhw0169&token_type=Bearer&expires_in=3599";
	
	@BeforeClass
	 public void beforeClass() {
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder().
		setBaseUri("https://gmail.googleapis.com").
		addHeader("Authorization", "Bearer "+ access_token).
		setContentType(ContentType.JSON).
		log(LogDetail.ALL);
		
		requestSpecification = requestBuilder.build();
		
		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder().expectStatusCode(200).
				expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		responseSpecification = responseBuilder.build();
 }
	

	@Test
public void getUserProfile() {
		
	given(requestSpecification).
		  basePath("/gmail/v1").
		  pathParam("userId","rahul.kshirsagar@lenskart.in").
		  when().
		    get("/users/{userId}/profile").
		then().spec(responseSpecification);
		
	  }
	
	@Test
	public void sendMessage() {
		String message = "From : rahul.kshirsagar@lenskart.in \n" +
	              "To: rahul.kshirsagar@lenskart.in\n" +
				"Subject: Test Email \n" +
	              "Sending from REST assured ";
		
		//To convert Base 64 URL encoded message 
		String base64URLEncoder = Base64.getUrlEncoder().encodeToString(message.getBytes());
		
		HashMap<String , String> payload = new HashMap<String, String>();
		payload.put("raw", base64URLEncoder);
		
		given(requestSpecification).
			  basePath("/gmail/v1").
			  pathParam("userId","rahul.kshirsagar@lenskart.in").
			  body(payload).
			  
			  when().
			    post("/users/rahul.kshirsagar@lenskart.in/messages/send").
			then().spec(responseSpecification);
			
		  }
}
