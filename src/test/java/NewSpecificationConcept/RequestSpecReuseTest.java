package NewSpecificationConcept;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

public class RequestSpecReuseTest {
	
	RequestSpecification requestSpec;
	
	@BeforeTest
	public void setup() {
		
		requestSpec = given().log().all()
				.baseUri("https://gorest.co.in")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
		
	}
	
	
	@Test
	public void getUserTest() {
		requestSpec.when()
				.get("/public/v2/users")
						.then()
								.statusCode(200);
	}
	
	
	@Test
	public void getAUserTest() {
		requestSpec.when()
				.get("/public/v2/users/7440218")
						.then()
								.statusCode(200);
	}
	
	@Test
	public void getWrongUserTest() {
		requestSpec.when()
				.get("/public/v2/users/1")
						.then()
								.statusCode(404);
	}
	
	
	

}
