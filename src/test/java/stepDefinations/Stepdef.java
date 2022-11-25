package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojousable.Location;
import pojousable.Pojoserialization;
import resources.ApiResources;
import resources.SpecBuilderTest;
import resources.Utils;


public class Stepdef extends Utils {
	
	SpecBuilderTest st=new SpecBuilderTest();
	
	RequestSpecification resq;
	ResponseSpecification E;
	Response resp;
	
	static String place_id;
	
	
	
	  @Given("Add place payload with {string} {string} {string}")
	    public void add_place_payload_with(String name,String language,String address) throws IOException  {
		  
		  
		 resq=given().spec(requestSpecification()).body(st.TestDataBuild(name,language,address));
		  
	        
	    }

	    @When("User calls {string} with {string} http request")
	    public void user_calls_add_place_api_wiht_post_http_request(String resources , String method)  {
	    	
	    	//The ValueOf() method is used to execute the constructor automatically.
	    	
	    	ApiResources resourceapi=ApiResources.valueOf(resources);
	    	System.out.println(resourceapi.getResource());
	    	
	    	
	    	 E=new ResponseSpecBuilder().expectStatusCode(200)
						.expectContentType(ContentType.JSON).build();
	    	 
	    	 
	    	if(method.equalsIgnoreCase("POST")) { 
	    	resp= resq.when().post(resourceapi.getResource());
	    	}
	    	
	    	else if(method.equalsIgnoreCase("GET")){
	    		resp= resq.when().get(resourceapi.getResource());
	    	}
	    	else if(method.equalsIgnoreCase("Delete")){
	    		resp= resq.when().delete(resourceapi.getResource());
	    	}
	    	
	      
	    }


	    @Then("The api call get success with the status code {int}")
	    public void the_api_call_get_success_with_the_status_code_something(Integer int1)  {
	    	assertEquals(resp.getStatusCode(),200);
	       
	    }

	    @And("{string} in the response body is {string}")
	    public void something_in_the_response_body_is_something(String keyvalue, String expectedvalue)  {
	    	
	    	
	    	
	    	
	    	assertEquals(getJsonPath(resp,keyvalue),expectedvalue);
	    }
	    	
	    	
	    	@And("verify the place_id created maps to {string} using {string}")
	    	public void verify_the_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    		
	    		//to get information of place id from the Jsonpath which was in getJsonPath method(declared in utils class).
	    		place_id=getJsonPath(resp,"place_id");
	    		
	    		//Below 2 steps:for getting the information we need to use get method and we need to have place_id for it .
	    		//we are adding place id as a query paramater.
	    		
	    		//NOTE: resp is storing a response in it.
	    		
	    		resq=given().spec(requestSpecification()).queryParam("place_id", place_id);
	    		
	    		
	    		//This Method is accepting 2 parameters .one is coming from feature file and u need to give as a input .
	    		user_calls_add_place_api_wiht_post_http_request(resource,"GET");
	    		
	    		String actualname=getJsonPath(resp,"name");
	    		assertEquals(actualname,expectedname);
	    		
	    		
	    		
	    		
	    	}
	    	
	    	@Given("Delete the given api")
	    	public void delete_the_given_api() throws IOException {
	    		
	    		resq=given().spec(requestSpecification()).body(st.deleteplacepayload(place_id));
	    	    
	    	}

	    
	        
	    

	

}
