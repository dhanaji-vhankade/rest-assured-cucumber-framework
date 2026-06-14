package resources;

public enum APIResources {
	
	
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdateAPI("/maps/api/place/update/json"),
	DeleteAPI("/maps/api/place/delete/json"),
	
	POST("POST"),
	GET("GET"),
	UPDATE("UPDATE"),
	DELETE("DELETE");
	
	

	private String resource;
	
	APIResources(String resource)
	{
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
