package api.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class InvalidData {
	
	
	
	@Test
	public void invalidEndpoint() {
		
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		Response res = RestAssured.given().headers("Content-Type","application/json").when().
				body("{\"username\" :\"ammuedamana@tekarch.com\",\"password\" :\"Admin123\"}").post("/login1");
		
		res.then().assertThat().statusCode(404).extract().response();
		
		System.out.println(res.asPrettyString());
	}
	
	@Test
	public void wrongRequest() {
		
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		Response res = RestAssured.given().headers("Content-Type","application/json").when().
				body("{\"username\" :\"ammuedamana@tekarch.com\",\"password\" :\"Admin123\"}").get("/login");
		
		res.then().assertThat().statusCode(400).extract().response();
		
		System.out.println(res.asPrettyString());
		
		
	}
	
	
	@Test
	public void invalidCredentials() {
		
		
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		Response res = RestAssured.given().headers("Content-Type","application/json").when().
				body("{\"username\" :\"123@tekarch.com\",\"password\" :\"Admin123\"}").post("/login");
		
		res.then().assertThat().statusCode(401).extract().response();
		
		System.out.println(res.asPrettyString());
		
		
	}
	
	
	@Test
	public void wrongJson() {
		
		
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		Response res = RestAssured.given().headers("Content-Type","application/javascript").when().
				body("{\"username\" :\"ammuedamana@tekarch.com\",\"password\" :\"Admin123\"}").post("/login");
		
		res.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(res.asPrettyString());
		
		
	}

}
