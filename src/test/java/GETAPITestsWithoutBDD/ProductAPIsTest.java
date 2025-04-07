package GETAPITestsWithoutBDD;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ProductAPIsTest {
	
	
	//without BDD
	@Test
	public void getProductsTest_1() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3NDQwMTYxNTR9.s-YYz2gPe79CEA3ExeRGrGrCo-Njn9382cI9PR_wJ-w");

		Response response = request.get("/contacts");
			
		int statusCode = response.statusCode();
		System.out.println("status code : " + statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		
		String statusLine = response.statusLine();
		System.out.println("status line : " + statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		String resBody = response.prettyPrint();
		System.out.println(resBody);
		
		
	}

}
