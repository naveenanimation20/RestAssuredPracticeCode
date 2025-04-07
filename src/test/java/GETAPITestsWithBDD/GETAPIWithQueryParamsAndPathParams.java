package GETAPITestsWithBDD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


import java.util.HashMap;
import java.util.Map;


public class GETAPIWithQueryParamsAndPathParams {
	
	@Test
	public void getUserWith_QueryParams() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		
		given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")

			.queryParam("name", "trivedi")
			.queryParam("status", "active")
				.when().log().all()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
									.statusCode(200)
											.and()	
												.contentType(ContentType.JSON);
						
		
	}
	
	
	
	@Test
	public void getUserWith_QueryParams_withHashMap() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		
//		Map<String, String> queryMap = new HashMap<String, String>();
//		queryMap.put("name", "trivedi");
//		queryMap.put("status", "active");
//		queryMap.put("gender", "male");
		
		Map<String, String> queryMap =	Map.of(
				"name", "trivedi",
				"status", "active"
				);

		
		given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")

			.queryParams(queryMap)
				.when().log().all()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
									.statusCode(200)
											.and()	
												.contentType(ContentType.JSON);
		
		
						
	}
	
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"7381532"},
//			{"7381533"},
//			{"7381534"}
		};
	}
	
	
	@Test(dataProvider = "getUserData")
	public void getUserAPI_WithPathParams(String userid) {
		
		RestAssured.baseURI = "https://gorest.co.in";

		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.pathParam("userid", userid)
						.when().log().all()
								.get("/public/v2/users/{userid}/posts")
								.then().log().all()
								.assertThat()
								.statusCode(200)
									.and()
										.body("title", hasItem("Comitatus reiciendis congregatio tandem temeritas certo crebro."));
		
	}
	
	
	//equalTo: json object {}
	//hasItem: Json Array []
	
	//jayway jsopath: $

}
