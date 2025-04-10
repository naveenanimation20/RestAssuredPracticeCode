package POSTAPIWithCreateUser;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	public String getRandomEmailId() {
	    return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
	}

	@Test
	public void getAuthTokenTest_WithJSONString() {

	    RestAssured.baseURI = "https://gorest.co.in";

	    String emailId = getRandomEmailId();

	    Integer userId = given().log().all()
	            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
	            .contentType(ContentType.JSON)
	            .body("{\n" +
	                    "    \"name\": \"seema\",\n" +
	                    "    \"gender\": \"female\",\n" +
	                    "    \"email\": \"" + emailId + "\",\n" +
	                    "    \"status\": \"inactive\"\n" +
	                    "}")
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
	
	
	@Test
	public void getAuthTokenTest_UsingStringReplacement() throws Exception {

	    RestAssured.baseURI = "https://gorest.co.in";
	    String email = getRandomEmailId();

	    String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json")));
	    String updatedJson = rawJson.replace("{{email}}", email);

	    Integer userId = given().log().all()
	            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
	            .contentType(ContentType.JSON)
	            .body(updatedJson)
	        .when().log().all()
	            .post("/public/v2/users")
	        .then().log().all()
	            .assertThat().statusCode(201)
	            .extract().path("id");

	    System.out.println("user id : " + userId);
	    Assert.assertNotNull(userId);
	}

}
