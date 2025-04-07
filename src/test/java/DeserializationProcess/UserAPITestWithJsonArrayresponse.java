package DeserializationProcess;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPITestWithJsonArrayresponse {
	
	
	
	@Test
	public void createUserWith_Lombok() {
		RestAssured.baseURI = "https://gorest.co.in";		
		
		//1. get all users using : GET
		Response getResponse = given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")	
			.when()
				.get("/public/v2/users");
				
		getResponse.prettyPrint();
		
		//De-Serialization: JSON to POJO
		ObjectMapper mapper = new ObjectMapper();
		try {
			User[] userRes = mapper.readValue(getResponse.getBody().asString(), User[].class);
			
			for(User user : userRes) {
				System.out.println("ID : " + user.getId());
				System.out.println("NAME : " + user.getName());
				System.out.println("EMAIL : " + user.getEmail());
				System.out.println("STATUS : " + user.getStatus());
				System.out.println("GENDER : " + user.getGender());
				
				System.out.println("----------------------");
				
			}

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
