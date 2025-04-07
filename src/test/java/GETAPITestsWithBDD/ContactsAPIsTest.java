package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPIsTest {
	
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	}
	
	
	@Test
	public void getContactsAPITest() {
		
		
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjUyOTIxNTh9.3FBGhtqXaTsZWnbFNTTTgwx9c96ZdehVQEhj-AnGV8c")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
									.statusCode(200)
										.and()
											.statusLine("HTTP/1.1 200 OK")
												.and()
													.contentType(ContentType.JSON)
														.and()
															.body("$.size()", equalTo(34));		
		
	}
	
	
	
	@Test
	public void getContactsAPITest_WithInvalidToken() {
				
		given().log().all()
			.header("Authorization", "Bearer -AnGV8c")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
									.statusCode(401);
										
											

	}

}
