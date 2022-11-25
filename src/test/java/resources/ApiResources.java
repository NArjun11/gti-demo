package resources;

//Enum is a class which contains collection of constants and method. 

public enum ApiResources {
	
	
	//simply write the String you want to use.
	
    addPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	
	private String resources;
	
	
	//for above methods to work we  need to write a constructor.
	
	ApiResources(String resources)
	{
		this.resources=resources;
	}
	public String getResource() {
		return resources;
	}

}
