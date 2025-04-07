package NewSpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest {
	
	
	@Test
	public void reqSpecTest() {
		
		RequestSpecification requestSpec = given().log().all()
			.baseUri("https://jsonplaceholder.typicode.com")
			.header("Content-Type", "application/json");
		
		//1.
		requestSpec
			.when()
				.get("/posts/1")
				.then()
				.statusCode(200);
		
		//2.
		requestSpec
			.when()
			.body("{\n"
					+ "    \"title\": \"foo\",\n"
					+ "    \"body\": \"bar\",\n"
					+ "    \"userId\": 1\n"
					+ "}")
			.when()
			.post("/posts")
			.then()
			.statusCode(201);
	}
	
	
	
	@Test
	public void getUserTest() {
		
		RequestSpecification requestSpec = given().log().all()
				.baseUri("https://gorest.co.in")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
		
		
		requestSpec.when()
						.get("/public/v2/users")
						.then()
						.statusCode(200);
		
		requestSpec.when()
			.get("/public/v2/users/7440218")
				.then()
					.statusCode(200);
		
	}
	
	
	@Test
	public void getUserTest_With_QueryParam() {
		
		RequestSpecification requestSpec = given().log().all()
				.baseUri("https://gorest.co.in")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.queryParam("name", "naveen");
				
		
		requestSpec.when()
						.get("/public/v2/users")
						.then()
						.statusCode(200);
		
		
		
	}
	
	
	
	

}
