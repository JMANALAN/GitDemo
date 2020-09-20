package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable
	{
		//if no existing place_id
		stepDefinition m = new stepDefinition();
		if(stepDefinition.place_id==null)
		{
			m.add_Place_Payload_with("Jen", "EN", "NA");
			m.user_calls_with_Post_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Jen", "GetPlaceAPI");
		}
	}
}
