package api.constants;

import api.utils.CommonUtilities;


public class FileConstants {
	
	
	public static final String URI_FILE_PATH = System.getProperty("user.dir")+"/src/main/java/api/testdata/uriconfig.json";
	public static final String USER_CONFIG_FILE_PATH = System.getProperty("user.dir")+"/src/main/java/api/testdata/userconfig.json";
	public static final String LOGIN_SCHEMA = System.getProperty("user.dir")+ "/src/main/java/api/testdata/schema/loginschema.json";
	public static final String GETDATA_SCHEMA = System.getProperty("user.dir")+ "/src/main/java/api/testdata/schema/getdataschema.json";
	public static final String ADD_DATA_SCHEMA = System.getProperty("user.dir") + "/src/main/java/api/testdata/schema/adddataschema.json";
	public static final String REPORT_PATH = System.getProperty("user.dir")+ "/src/main/resources/Reports/"+CommonUtilities.getStringDateAndTimestamp()+"_USER_API.html";
	


	
}
