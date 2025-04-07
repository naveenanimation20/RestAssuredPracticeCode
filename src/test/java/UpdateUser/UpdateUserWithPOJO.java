package UpdateUser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserWithPOJO {
	
	//1. create a user: POST: fetch the userid
	//2. GET
	//3. Update a user: PUT /userid
	
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	@Test
	public void updateUserWith_POJO() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("Pooja", getRandomEmailId(), "female", "active");
		
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
		
		
		//2. update: PUT: update a user
		user.setName("Pooja Sharma");
		user.setStatus("inactive");
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.body(user)
				.when()
					.put("/public/v2/users/" + userId)
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.body("id", equalTo(userId))
											.and()
												.body("name", equalTo(user.getName()))
												.and()
												.body("status", equalTo(user.getStatus()));
													
											
										
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
