package stepDefinitions;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	String rString;
    JsonPath js;
    
	
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		    // Write code here that turns the phrase above into concrete actions
					 
			 reqSpec=given().spec(requestSpecification())
			.body(data.addPlacePayload(name,language,address));
		}


	@When("User calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resource, String httpMethod) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if(httpMethod.equalsIgnoreCase("POST"))
			response = reqSpec.when().post(resourceAPI.getResource());
		else if(httpMethod.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(resourceAPI.getResource());
				
		//.then().spec(resSpec).extract().response();
		//String responseString = response.asString();
		//System.out.println(responseString);
	}

	@Then("^\"(.*?)\" result is \"(.*?)\"$")
	public void result_is(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(response.getStatusCode(),200);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String actValue, String expValue) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
	   	    
	    assertEquals(getJsonPath(response,actValue) .toString(),expValue);
	    System.out.println(actValue);
	    System.out.println(expValue);
		
	}

	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		 
		place_id = getJsonPath(response,"place_id");
		reqSpec=given().spec(requestSpecification())
		.queryParam("place_id",place_id);
		user_calls_with_Post_http_request(resource, "GET");
		String actName = getJsonPath(response,"name");
		
	    assertEquals(actName,expName);

	}

	@Given("Delete Place Payload with {string}  {string} {string}")
	public void delete_Place_Payload_with(String string, String string2, String string3) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    
		reqSpec = given().spec(requestSpecification())
		.body(data.deletePlacePayload(place_id));
	}


}
