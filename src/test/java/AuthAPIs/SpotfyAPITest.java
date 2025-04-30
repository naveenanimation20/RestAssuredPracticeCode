package AuthAPIs;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotfyAPITest {
	
private String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		RestAssured.baseURI = "https://accounts.spotify.com";
		
		Response response = RestAssured.given()
					.contentType(ContentType.URLENC)
					.formParam("grant_type", "client_credentials")
					.formParam("client_id", "ecfad8e76f534422810ca52f520e8edf")
					.formParam("client_secret", "b4f702b60450416fbfd181a221635509")
					.when()
						.post("/api/token");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		
		accessToken = response.jsonPath().getString("access_token");
	}
	
	
	@Test
	public void getAlbumTest() {
		
		Response response = RestAssured.given()
			.header("Authorization", "Bearer " + accessToken)
			.when()
			.get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
			
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		ReadContext ctx = JsonPath.parse(response.asString());
		
		//get all the tracks:
		List<String> tracks = ctx.read("$.tracks.items[*].artists[*].name");
		System.out.println(tracks);


		System.out.println("----------");
		
		//with two attributes:
		List<Map<String, Object>> jewleryList = ctx.read("$.images.[?(@.height == 300)].['url','width']");
		System.out.println(jewleryList);
		System.out.println(jewleryList.size());
		
		for(Map<String, Object> product : jewleryList) {
			String url = (String) product.get("url");
			Number width = (Number) product.get("width");
			System.out.println("url:" + url);
			System.out.println("width:" + width);
			System.out.println("-----------");
		}


	}	
	
//spotify jsonpath :
//	$.images.[?(@.height == 300)].['url','width']
//
//			$.images.[?(@.height == 300)].url
//
//			$.images[*]
//
//			$.images[*].url
//
//			$.images
//
//			$.id
//
//			$.external_urls
//
//			$.available_markets
//
//			$.tracks.href
//
//			$.tracks.items[*].artists[*].name
//
//			$.tracks.items[*].name

	
	
	

}
