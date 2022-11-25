package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@Deleteplace")
	public void beforeScenario() throws IOException {
		
		//Here write  code that will gives us place_id , we no need to relay on addplace to execute first.
		
		//create an object stepdefination class
		
		Stepdef s=new Stepdef();
		
		if(Stepdef.place_id==null) {
		
		s.add_place_payload_with("someone", "kannada", "somalia");
		s.user_calls_add_place_api_wiht_post_http_request("addPlaceApi", "POST");
		s.verify_the_place_id_created_maps_to_using("someone", "getPlaceApi");
		}
		
		
	}

}
