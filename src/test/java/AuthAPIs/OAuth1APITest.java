package AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth1APITest {
	
	//Oauth1.0: you need to add extra dependency in your pom.xml
	//name: scribejava-core
	//version: old version: 2.5.3
	
	
	@Test
	public void flickAPITest() {
		
		RestAssured.baseURI = "https://www.flickr.com";
		
		Response response = RestAssured.given()
					.auth()
					.oauth("a110d09788adcf0a3f867e4958a0a3ef", 
							"ecb0b3f9ab7d9f8c", 
							"72157720929991962-e1c25ed42b7c024c", 
							"0f4a1d7993ecac89")
					.queryParam("nojsoncallback", 1)
					.queryParam("format", "json")
					.queryParam("method", "flickr.test.login")
						.when()
							.get("/services/rest")
								.then()
									.assertThat()
										.statusCode(200)
											.extract()
												.response();
										
		response.prettyPrint();
		
	}
	
	
	
	
	

}
