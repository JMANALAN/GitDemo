package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	String rString;
    JsonPath js;

	public static RequestSpecification reqSpec;
	public RequestSpecification requestSpecification() throws IOException
	
	{
		if (reqSpec==null) 
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
		RestAssured.baseURI=getGlobalValue("baseURL");
		reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return reqSpec;
		}
			return reqSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fls = new FileInputStream("C:\\Users\\jenny.h.manalansang\\workspace\\CucumberAPIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fls);
		return(prop.getProperty(key));
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		
		rString = response.asString();
	    js = new JsonPath(rString);
	    return js.get(key).toString();
	    	    
	}
	
}
