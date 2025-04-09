package POSTAPIWithCreateUser;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	
	@Test
	public void getAuthTokenTest_WithJSONString() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Integer userId = given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.contentType(ContentType.JSON)
			.body("{\"name\":\"Baidehi\",\"email\":\"deshpande_baidehi_ret@open.example\",\"gender\":\"female\",\"status\":\"inactive\"}")
			.when().log().all()
				.post("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(201)
								.extract()
									.path("id");
		
		System.out.println("user id : " + userId);
		Assert.assertNotNull(userId);
	}
	
	
	
	@Test
	public void getAuthTokenTest_WithJSONFILE() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Integer userId = given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/jsons/user.json"))
			.when().log().all()
				.post("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(201)
								.extract()
									.path("id");
		
		System.out.println("user id : " + userId);
		Assert.assertNotNull(userId);
	}

}
