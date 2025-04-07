package RequestResponseSpecUsingBuilder;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;


public class RequestResponseSpecTest {
	
	static String accessToken;
	
	public static RequestSpecification oAuth2ReqSpec() {
		RequestSpecification requestOAuth2Spec = new RequestSpecBuilder()
			.setBaseUri("https://test.api.amadeus.com")
			.setContentType(ContentType.URLENC)
			.addFormParam("grant_type", "client_credentials")
			.addFormParam("client_id", "VXBJs37GsjZLfpjh7VqJrNdFLV0uryvV")
			.addFormParam("client_secret", "QO9X6yaeB1HfANf7")
			.build();
		return requestOAuth2Spec;
	}
	
	
	
	@BeforeMethod
	public void getAccessToken() {
		
		Response response =  given()
			.spec(oAuth2ReqSpec())
				.when()
					.post("/v1/security/oauth2/token");
		response.prettyPrint();
		accessToken = response.jsonPath().getString("access_token");
	}
	
	
	
	
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
