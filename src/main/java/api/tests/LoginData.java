package api.tests;

import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;

import api.constants.FileConstants;
import api.testdata.AddUser;
import api.utils.CommonUtilities;
import apiReusabilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginData extends ApiBaseTest {

	//String token;
	String uri ="";
	
	

	@BeforeClass
	public void setURI() throws IOException {
		uri = CommonUtilities.readFileAndReturnString(FileConstants.URI_FILE_PATH);
		RestAssured.baseURI = JsonPath.read(uri, "$.login.prod");
		System.out.println(RestAssured.baseURI);
	}

	@Test
	public void LoginTC() throws IOException {

		ApiBaseTest.test = extent.createTest("login");
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		test.info("Created Login reguest");
		String creds = CommonUtilities.readFileAndReturnString(FileConstants.USER_CONFIG_FILE_PATH);
		String un = JsonPath.read(creds, "$.prod.username");
		String pw = JsonPath.read(creds, "$.prod.password");
		
		
		  HashMap<String, String> logincreds = new HashMap<>();
		  logincreds.put("username", un); 
		  logincreds.put("password", pw);
		 
		test.info("Test Success");

		Response res1 = RestUtils.postReq(logincreds, headers, JsonPath.read(uri, "$.login.endpoints.login")).then().assertThat().statusCode(201).extract().response();
		
		res1.then().assertThat().body(matchesJsonSchema(new File(FileConstants.LOGIN_SCHEMA)));
		ApiBaseTest.extent.flush();

		System.out.println(res1.asPrettyString());

		
	}

	//@Test
	public void GetDataTC() {

		HashMap<String, String> headers = new HashMap<>();
		headers.put("Token", token);
		headers.put("Content-Type", "application/json");

		Response getUserData = RestUtils.getReq(headers, JsonPath.read(uri, "$.login.endpoints.getdata"));
		getUserData.then().assertThat().statusCode(200).extract().response();
		
		getUserData.then().assertThat().body(matchesJsonSchema(new File(FileConstants.GETDATA_SCHEMA)));

		System.out.println(getUserData.prettyPrint());

	}

	@Test
	public void AddDataTC() throws JsonProcessingException {
		
		ApiBaseTest.test = extent.createTest("AddDataTC");

		HashMap<String, String> headers = new HashMap<>();
		headers.put("Token", token);
		headers.put("Content-Type", "application/json");

		
		
		AddUser adduser = new AddUser("TA-1113481", "3", "22400", "111234");
		
		String sPayload = CommonUtilities.serializeObject(adduser);

		Response addUserData = RestUtils.postReq(sPayload, headers,JsonPath.read(uri, "$.login.endpoints.adddata"));
		addUserData.then().assertThat().statusCode(201).extract().response();
		
		addUserData.then().assertThat().body(matchesJsonSchema(new File(FileConstants.ADD_DATA_SCHEMA)));
		test.info("Test Success");
		System.out.println(addUserData.prettyPrint());
		ApiBaseTest.extent.flush();

	}
	
	//@Test()
	public void UpdateData() {
		
		
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Token", token);
		headers.put("Content-Type", "application/json");
		
		HashMap<String, String> payload = new HashMap<>();
		payload.put("accountno", "");
		payload.put("departmentno", "");
		payload.put("salary", "");
		payload.put("pincode", "");
		payload.put("userid", "");
		payload.put("id", "");

		Response updateData = RestUtils.putReq(payload, headers,JsonPath.read(uri , "$.login.endpoints.updatedata"));
		updateData.then().assertThat().statusCode(200).extract().response();
		System.out.println(updateData.prettyPrint());

	}
	//@Test
	public void deleteData() {
		

		HashMap<String, String> headers = new HashMap<>();
		headers.put("Token", token);
		headers.put("Content-Type", "application/json");
		
		HashMap<String, String> payload = new HashMap<>();
		payload.put("id", "ingRYkXn9f8uXs7IcnRW");
		payload.put("userid", "aswoxb8WWmJSI8oHNSDc");
		
		Response deleteData = RestUtils.deleteReq(payload, headers, JsonPath.read(uri, "$.login.endpoints.deletedata"));
		deleteData.then().assertThat().statusCode(200).extract().response();
		System.out.println(deleteData.asPrettyString());
	}
	
	
	@Test
	public void logoutDataTC() {
		
		

		HashMap<String, String> headers = new HashMap<>();
		headers.put("Token", token);
		headers.put("Content-Type", "application/json");
		
		HashMap<String, String> payload = new HashMap<>();
		payload.put("token", "null");
		
		Response logoutData = RestUtils.postReq(payload, headers,JsonPath.read(uri, "$.login.endpoints.logoutdata"));
		logoutData.then().assertThat().statusCode(200).extract().response();
		
		
	}

}
