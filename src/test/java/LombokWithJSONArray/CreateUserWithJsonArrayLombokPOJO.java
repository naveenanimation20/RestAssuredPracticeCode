package LombokWithJSONArray;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

public class CreateUserWithJsonArrayLombokPOJO {
	
	@Test
	public void createUserWithJsonArrayPOJO(){
		
		RestAssured.baseURI = "http://httpbin.org";
		
		User.UserData userData1 = new User.UserData(1, "abhi@g.com", "abhi", "anand", "https://reqres.in/img/faces/3-image.jpg");
		User.UserData userData2 = new User.UserData(2, "sonia@g.com", "sonia", "sharma", "https://reqres.in/img/faces/4-image.jpg");
		User.UserData userData3 = new User.UserData(3, "pooja@g.com", "pooja", "tiwari", "https://reqres.in/img/faces/5-image.jpg");
		User.UserData userData4 = new User.UserData(4, "surya@g.com", "surya", "arya", "https://reqres.in/img/faces/6-image.jpg");
		User.UserData userData5 = new User.UserData(5, "naveen@g.com", "naveen", "khunteta", "https://reqres.in/img/faces/7-image.jpg");
		User.UserData userData6 = new User.UserData(6, "kavya@g.com", "kavya", "kulkarni", "https://reqres.in/img/faces/8-image.jpg");

		
		User.Support support = new User.Support("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
		
		
		User user = new User(
				1,
				6,
				12,
				2, 
				Arrays.asList(userData1,userData2,userData3,userData4,userData5,userData6),
				support
				);
		
		
		given().log().all()
			.contentType(ContentType.JSON)
				.body(user)
					.when().log().all()
						.post("/post")
							.then()
								.assertThat()
									.statusCode(200);
	}
	
	
	
	
	
	
	
	
	
	
	

}
