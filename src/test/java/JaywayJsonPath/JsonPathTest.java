package JaywayJsonPath;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathTest {

	@Test
	public void getProductAPITest_JsonPath() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		ReadContext ctx = JsonPath.parse(jsonResponse);

		List<Number> prices = ctx.read("$[?(@.price > 50)].price");
		System.out.println(prices);

		List<Integer> ids = ctx.read("$[?(@.price > 50)].id");
		System.out.println(ids);

		List<Double> rates = ctx.read("$[?(@.price > 50)].rating.rate");
		System.out.println(rates);

		List<Integer> counts = ctx.read("$[?(@.price > 50)].rating.count");
		System.out.println(counts);

		for (int i = 0; i < ids.size(); i++) {
			System.out.println(ids.get(i) + " " + prices.get(i));
		}

		
		// $[?(@.rating.rate < 3)].id
		
		
	}

	@Test
	public void productApiTest_ConditionalExamples_WithTwoAttributes() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		ReadContext ctx = JsonPath.parse(jsonResponse);

		//single attribute:
		//$[?(@.rating.rate < 3)].id
		
		//with two attributes:
		List<Map<String, Object>> jewleryList = ctx.read("$[?(@.category == 'jewelery')].['title','price']");
		System.out.println(jewleryList);
		System.out.println(jewleryList.size());
		
		for(Map<String, Object> product : jewleryList) {
			String title = (String) product.get("title");
			Number price = (Number) product.get("price");
			System.out.println("title:" + title);
			System.out.println("price:" + price);
			System.out.println("-----------");
		}
		

	}
	
	
	
	
	@Test
	public void productApiTest_ConditionalExamples_WithThreeAttributes() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		ReadContext ctx = JsonPath.parse(jsonResponse);

		//single attribute:
		//$[?(@.rating.rate < 3)].id
		
		//with two attributes:
		List<Map<String, Object>> jewleryList = ctx.read("$[?(@.category == 'jewelery')].['title','price','id']");
		System.out.println(jewleryList);
		System.out.println(jewleryList.size());
		
		for(Map<String, Object> product : jewleryList) {
			String title = (String) product.get("title");
			Number price = (Number) product.get("price");
			Integer id = (Integer) product.get("id");

			System.out.println("title:" + title);
			System.out.println("price:" + price);
			System.out.println("id:" + id);
			System.out.println("-----------");
		}
		

	}
	
	
	//4 attributes: $[?(@.category == 'jewelery')].['title','price','id','category']
	
	//nested attributes: $[?(@.category == 'jewelery')].rating.rate
	//$[?(@.category == 'jewelery')].rating.count
	
	//$[?((@.category == 'jewelery') && (@.price < 20))].id
	//$[?((@.category == 'jewelery') && (@.price < 20))].['id','title']
	//$[?(@.rating.rate == '3.3') && (@.price < 20))].id
	
	//$[?((@.category == 'jewelery') && (@.price < 20))].['id','title']
	
	//$[?((@.rating.rate == '3.3') && (@.price > 20))].title
	//$[?((@.rating.rate == '3.3') && (@.price > 20))].['id','title']
	
	//$[?( (@.id == 3) && (@.username == 'kevinryan') )].address.city
	
	
	
	//single attribute: List<?>
	//multiple attributes: List<Map<String, Object>>

	

}
