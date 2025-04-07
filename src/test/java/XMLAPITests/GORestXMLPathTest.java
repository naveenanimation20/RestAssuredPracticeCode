package XMLAPITests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

public class GORestXMLPathTest {

	@Test
	public void gorestAPITest() {

		RestAssured.baseURI = "https://gorest.co.in";

		Response response = given().when().get("/public/v2/users.xml").then().extract().response();

		String responsebody = response.getBody().asString();
		XmlPath Xmlpath = new XmlPath(responsebody);

		// fetch objects type="array"
		String objtype = Xmlpath.getString("objects.@type");
		System.out.println("object type is :" + objtype);
		System.out.println("------------");

		// fetch id type="integer"
		List<String> idtype = Xmlpath.getList("objects.object.id.@type");
		System.out.println("id type is :" + idtype);
		for (String e : idtype) {
			System.out.println(e);
			Assert.assertEquals(e, "integer");
		}

		System.out.println("------------");

		// fetch all id values
		List<String> ids = Xmlpath.getList("objects.object.id");
		System.out.println(ids);
		System.out.println(ids.size());
		for (String i : ids) {
			System.out.println(ids.contains("7415666"));
			break;
		}
		System.out.println("------------");

	}

	@Test
	public void Xmltest_With_Deserialization() {
	    RestAssured.baseURI = "https://gorest.co.in";

	    Response response = given().when().get("/public/v2/users.xml").then().extract().response();

	    // create an object of XmlMapper class
	    XmlMapper mapper = new XmlMapper();

	    try {
	        //De-serialization of XML response into UserData class
	        UserData user = mapper.readValue(response.getBody().asString(), UserData.class);

	        //Accessing values for the first object in the list
	        System.out.println("id is : " + user.getObjects().get(0).getId().getValue()); 
	        System.out.println("email is : " + user.getObjects().get(0).getEmail());
	        System.out.println("gender is : " + user.getObjects().get(0).getGender());
	        System.out.println("name is : " + user.getObjects().get(0).getName());
	        System.out.println("status is : " + user.getObjects().get(0).getStatus());

	        // Accessing the root level attribute type
	        System.out.println("objects type is : " + user.getType());

	        // Accessing the type attribute for the first id
	        System.out.println("id type is : " + user.getObjects().get(0).getId().getType()); 

	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }
	}




}
