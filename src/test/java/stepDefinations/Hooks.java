package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.Before;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class Hooks {
	ResponseSpecification resSpec;
	Response response;
	String placeId;

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		StepDefination sd = new StepDefination();
		
		if (StepDefination.placeId == null) {
			sd.add_place_payload_with("Dhanaji", "Marathi", "Kupwad");
			sd.user_call_with_post_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Dhanaji", "GetPlaceAPI");
		}
		sd.delete_place_payload();
	}

}
