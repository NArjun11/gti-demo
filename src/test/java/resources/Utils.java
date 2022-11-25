package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	
	public static RequestSpecification res;
	
	
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(res==null) {
		//logging response and request globally.
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		
		//Here we are doing serialization(Building a java-body and adding those information to body , The restassured will convert it in to json.)
		
	
		//using request specbuilder
		res=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.addQueryParam("key","qaclick123")
				.build();
		return res;
		}
		return res;
	}
	
	public String getGlobalValue(String key) throws IOException {
		//using this class you can scan any file that is having .properties extension.
		Properties prop=new Properties();
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Arjun Kumar\\eclipse-workspace\\Frame\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response resp,String key) {
		
		String sResp=resp.asString();
    	JsonPath js=new JsonPath(sResp);
    	return js.get(key).toString();
    	
	}

}
