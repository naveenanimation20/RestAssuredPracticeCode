package RequestResponseSpecUsingBuilder;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;


public class ResponseSpecBuilderTest {
	
	
	public static ResponseSpecification get_res_spec_200OK() {
		ResponseSpecification responseSpec =	new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
			.build();
		
		return responseSpec;
	}
	
	public static ResponseSpecification get_res_spec_401_AuthFail() {
		ResponseSpecification responseSpec =	new ResponseSpecBuilder()
			.expectStatusCode(401)
			.expectHeader("Server", "cloudflare")
			.build();
		
		return responseSpec;
	}
	
	
	@Test
	public void getUsersTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users")
						.then()	
							.assertThat()
								.spec(get_res_spec_200OK());
							
	}
	
	@Test
	public void getUsers_WithInvalidToken_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6989898989890000000")
				.when()
					.get("/public/v2/users")
						.then()	
							.assertThat()
								.spec(get_res_spec_401_AuthFail());
							
	}
	
	
	
	
	
	
	

}
