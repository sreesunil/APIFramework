package apiReusabilities;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {

	public static Response postReq(HashMap<String, String> payload, HashMap<String, String> headers, String enddpoint) {
		return RestAssured.given().headers(headers).when().body(payload).post(enddpoint);

	}

	public static Response getReq(HashMap<String, String> headers, String enddpoint) {
		return RestAssured.given().headers(headers).when().get(enddpoint);

	}
	
	public static Response postReq(String payload, HashMap<String, String> headers, String enddpoint) {
		return RestAssured.given().headers(headers).when().body(payload).post(enddpoint);
	}


	public static Response putReq(HashMap<String, String> payload,HashMap<String, String> headers, String enddpoint) {
		return RestAssured.given().headers(headers).when().body(payload).get(enddpoint);
	}
	
	public static Response deleteReq(HashMap<String, String> payload,HashMap<String, String> headers, String enddpoint) {
		return RestAssured.given().headers(headers).when().body(payload).get(enddpoint);
	}
}