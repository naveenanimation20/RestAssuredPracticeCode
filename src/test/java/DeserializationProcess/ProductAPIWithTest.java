package DeserializationProcess;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class ProductAPIWithTest {
	
	
	@Test
	public void getProductsTest_With_POJO_Desierlization() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
			.when()
				.get("/products");
					
		response.prettyPrint();
		
		//De-serialization: response json ---> POJO (Product)
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
		 Product[] product = mapper.readValue(response.getBody().asString(), Product[].class);
		 
		 System.out.println(Arrays.toString(product));
		 
		 for(Product p : product) {
			 System.out.println("ID : " + p.getId());
			 System.out.println("Title: " + p.getTitle());
			 System.out.println("Price: " + p.getPrice());
			 System.out.println("Rate: " + p.getRating().getRate());
			 System.out.println("Count: " + p.getRating().getCount());

			 System.out.println("------------");

		 }
		 
		 
		 
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		
		
	}
	
	
	
	

}
