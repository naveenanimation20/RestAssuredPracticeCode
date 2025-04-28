package RequestResponseSpecUsingBuilder;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth2SpecTest {
	
	
static String accessToken;
	
	public static RequestSpecification oAuth2ReqSpec() {
		RequestSpecification requestOAuth2Spec = new RequestSpecBuilder()
			.setBaseUri("https://test.api.amadeus.com")
			.setContentType(ContentType.URLENC)
			.addFormParam("grant_type", "client_credentials")
			.addFormParam("client_id", "47Ae5NUW3JK1AzmHO6AGdANAjAcSJ6D0")
			.addFormParam("client_secret", "KRBtzUdpBgSol6Kz")
			.build();
		return requestOAuth2Spec;
	}
	
	
	
	@Test
	public void getAccessToken() {
		
		Response response =  given()
			.spec(oAuth2ReqSpec())
				.when()
					.post("/v1/security/oauth2/token");
		response.prettyPrint();
		accessToken = response.jsonPath().getString("access_token");
		Assert.assertNotNull(accessToken);
	}
	


}
