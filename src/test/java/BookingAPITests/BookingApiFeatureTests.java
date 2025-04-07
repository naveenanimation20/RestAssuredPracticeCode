package BookingAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookingApiFeatureTests {
	
	String tokenId;
	
	
	@BeforeMethod
	public void getToken() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		 tokenId = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/jsons/auth.json"))
			.when().log().all()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
								
		System.out.println("tokenId ==>" + tokenId);
	}
	
	
	@Test
	public void getBookingIdsTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		Response response = given().log().all()
			.when()
				.get("/booking")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.extract()
										.response();
		
		JsonPath js = response.jsonPath();
		
		List<Integer> allIds = js.getList("bookingid");
		System.out.println("total booking ids: " + allIds.size());

		//select count(*) from booking; --> x
		
		for(Integer id : allIds) {
			Assert.assertNotNull(id);
		}
										
	}
	
	

	@Test
	public void getBookingIdTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		//create a new booking id: POST
		int newBookingId = createBooking();
		
		//get the same booking id: GET
		given()
			.pathParam("bookingId", newBookingId)
				.when().log().all()
					.get("/booking/{bookingId}")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.body("firstname", equalTo("Jim"))
										.and()
										.body("bookingdates.checkin", equalTo("2018-01-01"));

						
	}
	
	
	@Test
	public void createBookingTest() {
		Assert.assertNotNull(createBooking());		
	}
	
	@Test
	public void updateBookingTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		//create a new booking id: POST
		int newBookingId = createBooking();

		//get the same booking id: GET
		verifyBookingId(newBookingId);
				
		//update the same booking id: PUT
				given()
				.pathParam("bookingId", newBookingId)
				.contentType(ContentType.JSON)
				.header("Cookie", "token="+tokenId)
				.body("{\n"
						+ "    \"firstname\" : \"Naveen\",\n"
						+ "    \"lastname\" : \"Automation\",\n"
						+ "    \"totalprice\" : 111,\n"
						+ "    \"depositpaid\" : true,\n"
						+ "    \"bookingdates\" : {\n"
						+ "        \"checkin\" : \"2018-01-01\",\n"
						+ "        \"checkout\" : \"2019-01-01\"\n"
						+ "    },\n"
						+ "    \"additionalneeds\" : \"Dinner\"\n"
						+ "}")
					.when().log().all()
						.put("/booking/{bookingId}")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.body("firstname", equalTo("Naveen"))
											.and()
											.body("lastname", equalTo("Automation"))
											.and()
											.body("additionalneeds", equalTo("Dinner"));

		
	}
	
	
	@Test
	public void partialBookingTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		//create a new booking id: POST
		int newBookingId = createBooking();

		//get the same booking id: GET
		verifyBookingId(newBookingId);
				
		//update the same booking id: PATCH
				given()
				.pathParam("bookingId", newBookingId)
				.contentType(ContentType.JSON)
				.header("Cookie", "token="+tokenId)
				.body("{\n"
						+ "    \"firstname\" : \"API\",\n"
						+ "    \"lastname\" : \"Automation\"\n"
						+ "}")
					.when().log().all()
						.patch("/booking/{bookingId}")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.body("firstname", equalTo("API"))
											.and()
											.body("lastname", equalTo("Automation"));
											

		
	}
	
	
	
	@Test
	public void deleteBookingTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		//create a new booking id: POST
		int newBookingId = createBooking();

		//get the same booking id: GET
		verifyBookingId(newBookingId);
				
		//delete the same booking id: DELETE
				given()
				.pathParam("bookingId", newBookingId)
				.contentType(ContentType.JSON)
				.header("Cookie", "token="+tokenId)
					.when().log().all()
						.delete("/booking/{bookingId}")
							.then().log().all()
								.assertThat()
									.statusCode(201);
	}
	
	
	
	
	public void verifyBookingId(int newBookingId) {
		
		//get the same booking id: GET
		given()
			.pathParam("bookingId", newBookingId)
				.when().log().all()
					.get("/booking/{bookingId}")
						.then().log().all()
							.assertThat()
								.statusCode(200);
		
	}
	
	
	
	
	public int createBooking() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
	int bookingId =	given()
			.contentType(ContentType.JSON)
				.body("{\n"
						+ "    \"firstname\" : \"Jim\",\n"
						+ "    \"lastname\" : \"Brown\",\n"
						+ "    \"totalprice\" : 111,\n"
						+ "    \"depositpaid\" : true,\n"
						+ "    \"bookingdates\" : {\n"
						+ "        \"checkin\" : \"2018-01-01\",\n"
						+ "        \"checkout\" : \"2019-01-01\"\n"
						+ "    },\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\n"
						+ "}")
				.when()
					.post("/booking")
						.then()
							.extract()
								.path("bookingid");
	
	System.out.println("booking id: " + bookingId);
	return bookingId;
		
	}
	
	//jsonpath: rest assured
	//jayway: jsonpath: query
	
	

}
