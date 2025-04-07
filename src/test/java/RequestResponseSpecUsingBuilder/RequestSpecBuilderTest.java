package RequestResponseSpecUsingBuilder;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class RequestSpecBuilderTest {
	
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec =	new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.build();
		return requestSpec;
	}
	
	
	@Test
	public void getUser_WithReq_Spec() {
		given()
			.spec(user_req_spec())
				.when()
					.get("/public/v2/users")
						.then()
							.statusCode(200);
	}
	
	
	@Test
	public void getUser_WithReq_Spec_QueryParam() {
		given().log().all()
			.queryParam("name", "naveen")
			.queryParam("status", "active")
			.spec(user_req_spec())
				.when()
					.get("/public/v2/users")
						.then()
							.statusCode(200);
	}
	
	

}
