package UpdateUser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTestWithUserObjectAndLombok {
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	
	@Test
	public void CreateUserWith_UserObject() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("Pooja", getRandomEmailId(), "female", "active");
		
		//1. post: create a user:
		Response postResponse = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.body(user)
				.when()
					.post("/public/v2/users");
						
		postResponse.prettyPrint();
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("user id ===> " + userId);
		
		System.out.println("===============================");
		
		
		//now get the same user:
		given().log().all()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.when()
				.get("/public/v2/users/"+userId)
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("id", equalTo(userId));
											
										
	}
	
	
	
	@Test
	public void CreateUserWith_Lombok() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		//User user = new User("Pooja", "poojaapi@gmail.com", "female", "active");
		
		UserLombok user = new UserLombok("Pooja", getRandomEmailId(), "female", "active");
		
		
		//1. post: create a user:
		Response postResponse = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.body(user)
				.when().log().all()
					.post("/public/v2/users");
						
		postResponse.prettyPrint();
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("user id ===> " + userId);
		
		System.out.println("===============================");
		
											
										
	}
	
	@Test
	public void CreateUserWith_Lombok_Builder() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		//creating user class object using lombok builder pattern:	
				
		UserLombok userLombok =	new UserLombok.UserLombokBuilder()
				.name("Pooja")
				.email(getRandomEmailId())
				.status("active")
				.gender("female")
				.build();
			
		
		//1. post: create a user:
		Response postResponse = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.body(userLombok)
				.when().log().all()
					.post("/public/v2/users");
						
		postResponse.prettyPrint();
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("user id ===> " + userId);
		
		System.out.println("===============================");
//		
											
										
	}
	
	
	

	
	
	
	
	

}
