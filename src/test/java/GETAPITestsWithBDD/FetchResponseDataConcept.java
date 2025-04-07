package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseDataConcept {
	
	@Test
	public void getContactsAPITest_WithInvalidToken() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

		given().log().all()
			.header("Authorization", "Bearer -AnGV8c")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
									.statusCode(401)
										.and()
											.body("error", equalTo("Please authenticate."));

	}
	
	
	@Test
	public void getContactsAPITest_WithInvalidToken_WithExtract() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

		String errorMesg = given().log().all()
			.header("Authorization", "Bearer -AnGV8c")
				.when().log().all()
					.get("/contacts")
						.then()
							.extract()
								.path("error");
		
		System.out.println(errorMesg);
		Assert.assertEquals(errorMesg, "Please authenticate.");

	}
	
	
	@Test
	public void getSingleUser_FetchSingleUSerData() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users/7381475");
						
		
		System.out.println("status code: " + response.statusCode());
		System.out.println("status line: " + response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		int userId = js.getInt("id");
		System.out.println("user id : " + userId);
		
		String userName = js.getString("name");
		System.out.println("user name : " + userName);
		
		String status = js.getString("status");
		System.out.println("status : " + status);		
		
	}
	
	
	
	@Test
	public void getSingleUser_FetchFullUSerData() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users");
						
		
		System.out.println("status code: " + response.statusCode());
		System.out.println("status line: " + response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		List<Integer> ids = js.getList("id");
		System.out.println(ids);	
		
		//Assert.assertTrue(ids.contains("7383304"));
		
		List<String> names = js.getList("name");
		System.out.println(names);
		
		for(Integer e : ids) {
			System.out.println(e);
		}
	}
	
	
	
	
	@Test
	public void getProduct_FetchNestedData() {
		RestAssured.baseURI = "https://fakestoreapi.com";
				
		
		Response response = given()
				.when()
					.get("/products");
						
		
		System.out.println("status code: " + response.statusCode());
		System.out.println("status line: " + response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
				
		List<Integer> ids = js.getList("id");
		System.out.println(ids);
		
		List<Object> prices = js.getList("price");
		System.out.println(prices);
		
		List<Object> rateList = js.getList("rating.rate");
		System.out.println(rateList);
		
		List<Integer> countList = js.getList("rating.count");
		System.out.println(countList);
		
		
		for(int i=0; i<ids.size(); i++) {
			int id = ids.get(i);
			Object price = prices.get(i);
			Object rate = rateList.get(i);
			int count = countList.get(i);
			
			System.out.println("ID: " + id + " price : " + price + " rate : " + rate + " count : " + count );
		}
		
		Assert.assertTrue(rateList.contains(4.7f));
		
		
	}
	
	
	
	
	

	    public static void main(String[] args) {
	    	HashMap<String, String> pathParams = new HashMap<>();
	        pathParams.put("firstPath", "api");
	        pathParams.put("secondPath", "users");

	        RestAssured
	            .given()
	                .baseUri("https://reqres.in")
	                .pathParams(pathParams)  
	                .queryParam("page", 2)    
	            .when().log().all()
	                .get("/{firstPath}/{secondPath}") 
	            .then()
	                .statusCode(200)
	                .log().all();  
               
	    }
	}

	
	
	
	
	
	
	
	
	
	


