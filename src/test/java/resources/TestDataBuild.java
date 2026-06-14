package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceSerialization;
import pojo.Location;

public class TestDataBuild extends Utils {

	public AddPlaceSerialization addPlacePayload(String name, String lanugauge, String address) {

		AddPlaceSerialization addPlace = new AddPlaceSerialization();
		addPlace.setAccuracy(50);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress(address);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		addPlace.setTypes(myList);
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage(lanugauge);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		addPlace.setLocation(l);
		return addPlace;

	}

	public String deletePlacePayload(String placeId) {

		return "{\r\n" + "    \"place_id\":\"" + placeId + "\"\r\n" + "}\r\n" + "";

	}
}
