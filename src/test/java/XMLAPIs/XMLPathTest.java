package XMLAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathTest {

	@Test
	public void xmlTest() {
		XmlPath xmlPath = new XmlPath(new File("./src/test/resources/xmls/MRData.xml"));

		List<String> circuitNames = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("total circuits: " + circuitNames.size());
		System.out.println("--------------------");
		for (String e : circuitNames) {
			System.out.println(e);
		}
		
		System.out.println("----");
		String locality = xmlPath.getString("**.findAll{ it.@circuitId == 'americas' }.Location.Locality");
		System.out.println(locality);
	}
	
	

	@Test
	public void circuitApiTest_Xml() {
		RestAssured.baseURI = "http://ergast.com";

		Response response = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		String responseBody = response.body().asString();
		System.out.println(responseBody);

		XmlPath xmlPath = new XmlPath(responseBody);

		List<String> circuitNames = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("total circuits: " + circuitNames.size());
		System.out.println("--------------------");
		for (String e : circuitNames) {
			System.out.println(e);
		}

		System.out.println("--------------------");

		// fetch the Locality where circuitId = 'americas' ==> Austin

		String locality = xmlPath.getString("**.findAll{ it.@circuitId == 'americas' }.Location.Locality");
		System.out.println(locality);

		System.out.println("--------------------");

		String country = xmlPath.getString("**.findAll{ it.@circuitId == 'americas' }.Location.Country");
		System.out.println(country);
		System.out.println("--------------------");

		// fetch the Locality where circuitId = 'americas' or circuitId = 'bahrain' ==>
		// Austin, Sakhir

		List<String> moreLocalities = xmlPath
				.getList("**.findAll{ it.@circuitId == 'americas' || it.@circuitId == 'bahrain' }.Location.Locality");

		System.out.println(moreLocalities);

	}

	@Test
	public void xmlPathTest() {
		RestAssured.baseURI = "http://ergast.com";

		Response response = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		String responseBody = response.body().asString();
		System.out.println(responseBody);

		XmlPath xmlPath = new XmlPath(responseBody);

		List<String> circuitIds = xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");

		for (String e : circuitIds) {
			System.out.println(e);
		}

		System.out.println("--------------");

		// fetch the lat and long values where circuitid = 'bahrain'

		String latValue = xmlPath.getString("**.findAll{ it.@circuitId == 'bahrain' }.Location.@lat");
		String longValue = xmlPath.getString("**.findAll{ it.@circuitId == 'bahrain' }.Location.@long");
		System.out.println(latValue);
		System.out.println(longValue);

		System.out.println("--------------");

		// fetch url where circuitid = 'americas'

		String americaUrl = xmlPath.getString("**.findAll{ it.@circuitId == 'americas'}.@url");
		System.out.println(americaUrl);
		System.out.println("--------------");

	}


	@Test
	public void testFetchUserById() {
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = RestAssured.given().when().get("/public/v2/users.xml").then().extract().response();

		XmlPath xmlPath = new XmlPath(response.getBody().asString());

		//Fetch the name and email where id = 7457968
		String expectedId = "7871917";
		String name = xmlPath.getString("objects.object.find { it.id.text() == '" + expectedId + "' }.name");
		String email = xmlPath.getString("objects.object.find { it.id.text() == '" + expectedId + "' }.email");

		System.out.println("Name: " + name);
		System.out.println("Email: " + email);

	}

	@Test
	public void testFetchUserIdByName() {
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = RestAssured.given().when().get("/public/v2/users.xml").then().extract().response();

		XmlPath xmlPath = new XmlPath(response.getBody().asString());

		//Fetch the id where name = "Anunay Khatri"
		String userName = "Anunay Khatri";
		String userId = xmlPath.getString("objects.object.find { it.name.text() == '" + userName + "' }.id");

		//Print the fetched user ID
		System.out.println("User ID for " + userName + ": " + userId);

	}
	
	
	@Test
    public void getEmailByNameUsingGPath() {
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = RestAssured.given().when().get("/public/v2/users.xml").then().extract().response();

		XmlPath xmlPath = new XmlPath(response.getBody().asString());

        //Extract email using GPath
		List<String> emails = xmlPath.getList("**.findAll{ it.name == 'Anunay Khatri' }.email");
		System.out.println(emails);

		// Example assertion for single expected email:
		Assert.assertEquals(emails.get(0), "anunay_khatri@murphy.test", "Email mismatch");
    }
	
	@Test
    public void getNameAndEmailByIdUsingGPath() {
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = RestAssured.given().when().get("/public/v2/users.xml").then().extract().response();

		XmlPath xmlPath = new XmlPath(response.getBody().asString());

        //Extract name and email using GPath where id is 7462568
        String name = xmlPath.getString("**.findAll{ it.id == '7871906' }.name");
        String email = xmlPath.getString("**.findAll{ it.id == '7871906' }.email");
        System.out.println(name);
        System.out.println(email);

        //Validate the name and email
        Assert.assertEquals(name, "Chakrika Naik", "Name mismatch");
        Assert.assertEquals(email, "chakrika_naik@sanford.test", "Email mismatch");
    }
	
	
	@Test
    public void getNameEmailAndIdTypeByIdUsingGPath() {
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = RestAssured.given().when().get("/public/v2/users.xml").then().extract().response();

		XmlPath xmlPath = new XmlPath(response.getBody().asString());

        //Extract name, email, and id with type where id is 7462568
        String name = xmlPath.getString("objects.object.findAll{ it.id == '7462568' }.name");
        String email = xmlPath.getString("objects.object.findAll{ it.id == '7462568' }.email");
        String id = xmlPath.getString("objects.object.findAll{ it.id == '7462568' }.id");
        String type = xmlPath.getString("objects.object.findAll{ it.id == '7462568' }.id.@type"); // Extracting the type attribute

        System.out.println(name);
        System.out.println(email);
        System.out.println(id);
        System.out.println(type);

        
        //Validate the extracted values
        Assert.assertEquals(name, "Chakrika Naik", "Name mismatch");
        Assert.assertEquals(email, "chakrika_naik@sanford.test", "Email mismatch");
        Assert.assertEquals(id, "7462568", "ID mismatch");
        Assert.assertEquals(type, "integer", "Type mismatch");
    }
	

}
