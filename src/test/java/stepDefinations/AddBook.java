package stepDefinations;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.ExcelDataDriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddBook {
	@Test
	public void addBook() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";

		// Send Excel Data to Test

		ExcelDataDriven excelDataDriven = new ExcelDataDriven();

		ArrayList data = excelDataDriven.getData("RestAssured","RestData");

		// HashMap to Json
		HashMap<String, Object> hashMap = new HashMap();

		hashMap.put("name", data.get(1));
		hashMap.put("isbn", data.get(2));
		hashMap.put("aisle", data.get(3));
		hashMap.put("author", data.get(4));

		// For Nested Json
		HashMap<String, Object> nestedJson = new HashMap<String, Object>();
		nestedJson.put("lat", "123");
		nestedJson.put("lng", "345");
		hashMap.put("location", nestedJson);

		String response = given().log().all().headers("Content-Type", "application/json").body(hashMap).when()
				.post("/Library/Addbook.php").then().log().all().extract().response().asString();

		JsonPath js = new JsonPath(response);
		System.out.println("Msg is: " + js.getString("Msg"));

	}

}
