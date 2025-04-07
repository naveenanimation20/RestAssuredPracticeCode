package POSTWithAuthAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

import static io.restassured.RestAssured.given;

import java.io.File;


public class AuthAPITest {
	
	@Test
	public void getAuthTokenTest_WithJSON() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"username\" : \"admin\",\n"
					+ "    \"password\" : \"password123\"\n"
					+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
								
		System.out.println("tokenId ==>" + tokenId);
		Assert.assertNotNull(tokenId);
		
	}
	
	
	@Test
	public void getAuthTokenTest_WithJSONFILE() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/jsons/auth.json"))
			.when().log().all()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
								
		System.out.println("tokenId ==>" + tokenId);
		Assert.assertNotNull(tokenId);
		
	}
	
	//pojo --> json: serialization
	//json--->pojo: de-serialization
	//jackson-databind/gson/yasson
	@Test
	public void getAuthTokenTest_WithPOJOClass() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Credentials cred = new Credentials("admin", "password123");
		
		String tokenId = given().log().all()
			.contentType(ContentType.JSON)
			.body(cred) //pojo to json: serialization: ObjectMapper(Jackson databind)
			.when().log().all()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
								
		System.out.println("tokenId ==>" + tokenId);
		Assert.assertNotNull(tokenId);
		
		//json ---> pojo: De-serialization
		
		
	}
	
	
	
	

}
