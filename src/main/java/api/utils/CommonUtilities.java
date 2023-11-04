package api.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import api.testdata.AddUser;

import java.nio.file.Files;

public class CommonUtilities {
	
	
	public static String readFileAndReturnString(String filePath) throws IOException {
		byte[] fileContents = Files.readAllBytes(Paths.get(filePath));
		return new String(fileContents, StandardCharsets.UTF_8);
	}
	
	// To convert object to string and return the same
		public static String serializeObject(Object user) throws JsonProcessingException {
			
			ObjectMapper om = new ObjectMapper();
			om.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CASE);
			String sJsonPayload = om.writeValueAsString(user);
			System.out.println(sJsonPayload);
			return sJsonPayload;
			
		}
		
		public static Object deSerializeJSON(String sJson) throws JsonMappingException, JsonProcessingException {
			
			ObjectMapper om = new ObjectMapper();
			AddUser au = om.readValue(sJson, AddUser.class);
			return au;
			
		}
		
		public static String getStringDateAndTimestamp() {
			String value = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			return value;
		}
			

}
