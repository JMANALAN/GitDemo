package resources;

import java.util.ArrayList;
import java.util.List;

import pojoPackage.addPlace;
import pojoPackage.locationPlace;

public class TestDataBuild {

	public addPlace addPlacePayload(String name, String language, String address)
	{
		addPlace p = new addPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setLanguage(language);
		p.setAddress(address);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		locationPlace l = new locationPlace();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
