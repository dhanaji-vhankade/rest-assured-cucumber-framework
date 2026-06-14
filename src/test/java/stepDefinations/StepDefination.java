package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javac.util.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlaceSerialization;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
//	RequestSpecification reqSpecForAddPlace, reqSpecForGetPlace, reqSpecForUpdatePlace, reqSpecForDeletePlace;
	RequestSpecification req;
	Response response;
	String responseString;
	static String placeId;

	TestDataBuild data = new TestDataBuild();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		// Add place
		req = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
		System.out.println("Added by GitY");
		System.out.println("Added by GitX");
		System.out.println("Added by GitX on Develop branch");
		System.out.println("Added by GitY on Develop branch");

	}

	@When("User call {string} with {string} http request")
	public void user_call_with_post_http_request(String resource, String method) {

		APIResources apiResources = APIResources.valueOf(resource);
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST")) {
			response = req.when().post(apiResources.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = req.when().get(apiResources.getResource());
		} else if (method.equalsIgnoreCase("PUT")) {
			response = req.when().put();
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = req.when().delete(apiResources.getResource());
		}
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		response = response.then().spec(resSpec).extract().response();

		assertEquals(200, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		String actual_value = getJsonPath(response, keyValue);

		assertEquals("Value is not as expected", expectedValue, actual_value);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		placeId = getJsonPath(response, "place_id");

		req = given().spec(requestSpecification()).queryParam("place_id", placeId);

		user_call_with_post_http_request(resource, "GET");

		response = response.then().extract().response();

		assertEquals(expectedName, getJsonPath(response, "name"));

	}

	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		req = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

}
