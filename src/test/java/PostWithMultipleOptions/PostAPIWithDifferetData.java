package PostWithMultipleOptions;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.io.File;


public class PostAPIWithDifferetData {
	
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "hi this is naveen";
		
		given()
			.contentType(ContentType.TEXT)
			.body(payload)
				.when()
					.post("/post")
						.then().log().all()
							.assertThat()
								.statusCode(200);
		
	}
	
	@Test
	public void bodyWithJavaScriptTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<script>\n"
				+ "var x, y, z;  \n"
				+ "x = 5;    \n"
				+ "y = 6;    \n"
				+ "z = x + y;  \n"
				+ "document.getElementById(\"demo\").innerHTML =\n"
				+ "\"The value of z is \" + z + \".\";\n"
				+ "</script>";
		
		given()
			.contentType("application/javascript")
			.body(payload)
				.when()
					.post("/post")
						.then().log().all()
							.assertThat()
								.statusCode(200);
	}
	
	
	@Test
	public void bodyWithHTMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<html>\n"
				+ "<body>\n"
				+ "    <h2>Demo JavaScript in Head</h2>\n"
				+ "    <p id=\"demo\">A Paragraph.</p>\n"
				+ "    <button type=\"button\" onclick=\"myFunction()\">Try it</button>\n"
				+ "</body>\n"
				+ "</html>";
		
		given()
			.contentType(ContentType.HTML)
			.body(payload)
				.when()
					.post("/post")
						.then().log().all()
							.assertThat()
								.statusCode(200);
	}
	
	
	
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<note>\n"
				+ "<to>Tove</to>\n"
				+ "<from>Jani</from>\n"
				+ "<heading>Reminder</heading>\n"
				+ "<body>Don't forget me this weekend!</body>\n"
				+ "</note>";
		
		given()
			.contentType("application/xml;charset=utf-8")
			.body(payload)
				.when()
					.post("/post")
						.then().log().all()
							.assertThat()
								.statusCode(200);
	}
	
	
	
	@Test
	public void bodyWithMultiPartTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		
		given().log().all()
			.contentType(ContentType.MULTIPART)
			.multiPart("filename", new File("/users/naveenautomationlabs/Desktop/bzLifecycle.png"))
			.multiPart("name", "Bugzilla tool")
			
			.when()
				.post("/post")
				.then().log().all()
				.assertThat()
					.statusCode(200);
					
	}	
	
	
	@Test
	public void bodyWithPdfFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		
		given().log().all()
			.contentType("application/pdf")
			.body(new File("/Users/naveenautomationlabs/Desktop/hdfc.pdf"))
			.when()
				.post("/post")
				.then().log().all()
				.assertThat()
					.statusCode(200);
					
	}
	
	
	
	

}
