package JaywayJsonPath;

import static io.restassured.RestAssured.given;

import java.util.Set;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathMethodsTest {

	@Test
	public void getProductTest() {

		Response response = given().log().all().when().get("https://fakestoreapi.com/products")
					.then().extract()
									.response();

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		// min() - Provides the min value of an array of numbers
		Double minPrice = JsonPath.read(jsonResponse, "min($[*].price)");
		System.out.println("Minimum price: " + minPrice);

		
		System.out.println("-----------");
		
		
		
		// max() - Provides the max value of an array of numbers
		Double maxPrice = JsonPath.read(jsonResponse, "max($[*].price)");
		System.out.println("Maximum price: " + maxPrice);
		
		
		System.out.println("-----------");

		
		
		

		// avg() - Provides the average value of an array of numbers
		Double averagePrice = JsonPath.read(jsonResponse, "avg($[*].price)");
		System.out.println("Average price: " + averagePrice);
		
		
		System.out.println("-----------");

		
		

		// stddev() - Provides the standard deviation value of an array of numbers
		Double stdDev = JsonPath.read(jsonResponse, "stddev($[*].price)");
		System.out.println("Standard deviation: " + stdDev);
		
		System.out.println("-----------");


		// length() - Provides the length of an array
		Integer arrayLength = JsonPath.read(jsonResponse, "length($)");
		System.out.println("Array length: " + arrayLength);
		
		
		System.out.println("-----------");


		// sum() - Provides the sum value of an array of numbers
		Double sum = JsonPath.read(jsonResponse, "sum($[*].price)");
		System.out.println("Sum of prices: " + sum);
		
		
		System.out.println("-----------");

		

		// keys() - Provides the property keys (An alternative for terminal tilde ~)
		Set<String> keys = JsonPath.read(jsonResponse, "keys($)");
		System.out.println("Property keys: " + keys);
		
		
		System.out.println("-----------");
		

		// concat(X) - Provides a concatenated version of the path output with a new
		// item
		String concatResult = JsonPath.read(jsonResponse, "concat($[*].price, '0.00001')");
		System.out.println("Concatenated result: " + concatResult);
		
		System.out.println("-----------");


		// append(X) - Add an item to the JSON path output array
		Object appendResult = JsonPath.read(jsonResponse, "append($[*].price, '0.900001')");
		System.out.println("Appended result: " + appendResult.toString());
		
		
		System.out.println("-----------");


		// first() - Provides the first item of an array
		Object firstItem = JsonPath.read(jsonResponse, "first($[*].price)");
		System.out.println("First item: " + firstItem);
		
		System.out.println("-----------");

		

		// last() - Provides the last item of an array
		Object lastItem = JsonPath.read(jsonResponse, "last($[*].price)");
		System.out.println("Last item: " + lastItem);

		// index(X) - Provides the item of an array of index: X
		Object itemAtIndex = JsonPath.read(jsonResponse, "index($[*].title, 2)");
		System.out.println("Item at index 2: " + itemAtIndex);

	}

}
