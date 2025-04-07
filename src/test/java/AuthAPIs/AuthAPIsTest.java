package AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;

public class AuthAPIsTest {

	//basic
	//preemptive
	//digestive
	//form
	//Oauth1
	//Oauth2
	//JWT
	
	//basic auth: username and password
	//2
	@Test
	public void basicAuthTest() {
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		RestAssured.given()
						.auth()
							.basic("admin", "admin")
								.when()
									.get("/basic_auth")
										.then().log().all()
											.assertThat().statusCode(200);
	}
	
	//3.
	@Test
	public void preemptiveAuthTest() {
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		RestAssured.given()
						.auth()
							.preemptive()
								.basic("admin", "admin")
								.when()
									.get("/basic_auth")
										.then().log().all()
											.assertThat().statusCode(200);
	}
	
	
	//1. 
	@Test
	public void digestiveAuthTest() {
		RestAssured.baseURI = "https://postman-echo.com";
		RestAssured.given()
						.auth()
							.digest("postman", "password")
								.when()
									.get("/digest-auth")
										.then().log().all()
											.assertThat()
												.statusCode(200)
													.and()
														.body("authenticated", equalTo(true));
	}
	
	
	
	
	
	
}
