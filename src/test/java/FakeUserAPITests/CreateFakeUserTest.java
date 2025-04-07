package FakeUserAPITests;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class CreateFakeUserTest {
	
	
	@Test
	public void createUserTest_LombokPojo() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		//GeoLocation geoLocation1 = new GeoLocation("-98.900", "987.999");

		
		User.Address.GeoLocation geoLocation = new User.Address.GeoLocation("-98.900", "987.999");
		
		User.Address address = new User.Address("Bangalore", "new road", 9090, "98789", geoLocation);
		
		User.Name name = new User.Name("Sonia", "Sharma");
		
		User user = new User("sonia@gmail.com", "soniasharma", "sonia@123", "9898-9090-987", name, address);
		
		given().filter(new AllureRestAssured()).log().all()
			.body(user)
			.when()
				.post("/users")
					.then().log().all()
						.assertThat()
							.statusCode(200);
					
	}
	
	
	@Test
	public void createUserTest_LombokBuilder() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		
		User.Address.GeoLocation geoLocation =	new User.Address.GeoLocation.GeoLocationBuilder()
														.lat("-90.899")
															.longitude("876.33")
																	.build();
		
		
		User.Address address = new User.Address.AddressBuilder()
											.city("Pune")
												.street("old road")
													.number(123)
														.zipcode("98989")
															.geoLocation(geoLocation)	
																.build();
		
		User.Name name = new User.Name.NameBuilder()
								.firstname("Piyush")
										.lastname("Sharma")
											.build();
		
		
		User user = new User.UserBuilder()
							.email("piyush@gmail.com")
								.password("piyush@123")
									.phone("9876-090-987")
										.username("piyusapi")
											.name(name)
												.address(address)
													.build();
		
		
		given().log().all()
			.body(user)
			.when()
				.post("/users")
					.then().log().all()
						.assertThat()
							.statusCode(200);
					
	}
	
	
	@Test
	public void getAuthTokenTest_WithJSONFILE() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Integer userId = given().log().all()
				.header("Authorization", "Bearer 0614154e7579f37c6d56f45a438fbb4a281ad90c9ea8d9550dc947d948c7686f")
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/jsons/user.json"))
			.when().log().all()
				.post("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(201)
								.extract()
									.path("id");
		
		System.out.println("user id : " + userId);
		Assert.assertNotNull(userId);
		
		        int a = 10;
		        System.out.println(a += (a = 5) * (a / 5));
		    
	
	

}
}
