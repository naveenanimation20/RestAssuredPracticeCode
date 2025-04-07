package NewSpecificationConcept;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecificationTest {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	
	
	@BeforeTest
	public void setup() {
		//define request spec:
		requestSpec = given().log().all()
			.baseUri("https://jsonplaceholder.typicode.com")
			.header("Content-Type", "application/json");
		
		//define response spec:
		responseSpec = 	expect()
			.statusCode(anyOf(equalTo(200), equalTo(201)))
			.header("Content-Type", "application/json; charset=utf-8")
			.header("Server", "cloudflare")
			.time(lessThan(1500L));
	}
	
	
	
	@Test
	public void checkGetTest() {
		requestSpec
			.when()
				.get("/posts/1")
			.then()
				.spec(responseSpec)
				.body("userId", equalTo(1))
				.body("id", equalTo(1));

	}
	
	@Test
	public void checkGetWithQueryParamTest() {
		requestSpec
			.queryParam("userId", 1)
			.when()
				.get("/posts/1")
			.then()
				.spec(responseSpec)
				.body("userId", equalTo(1))
				.body("id", equalTo(1));

	}
	
	
	@Test
	public void checkPOSTTest() {
		requestSpec
			.body("{\n"
					+ "    \"title\": \"foo\",\n"
					+ "    \"body\": \"bar\",\n"
					+ "    \"userId\": 1\n"
					+ "}")
			.when()
				.post("/posts")
				.then()
				.spec(responseSpec)
				.body("userId", equalTo(1))
				.body("id", equalTo(101))
				.body("title", equalTo("foo"));

	}
	
	
	
	
	
	
	

}
