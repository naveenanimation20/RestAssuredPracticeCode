package RequestResponseSpecUsingBuilder;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RequestResponseSpecTest {
	
	
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec =	new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.build();
		return requestSpec;
	}
	
	public static ResponseSpecification get_res_spec_200OK() {
		ResponseSpecification responseSpec =	new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
			.build();
		
		return responseSpec;
	}
	
	@Test
	public void getUserTest() {
		
		given()
			.spec(user_req_spec())
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_res_spec_200OK());
		
	}
	
	
	

}
