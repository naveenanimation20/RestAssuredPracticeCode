package XMLAPITests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import XMLAPITests.MRData.Circuit;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CircuitAPIXMLTest {
	
	@Test
	public void xmlTest_With_XMLResponse_Desrialization() {

		RestAssured.baseURI = "http://ergast.com";

		Response response = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		// create the object of XmlMapper: deserialization
		XmlMapper xmlMapper = new XmlMapper();

		try {
			MRData mrData = xmlMapper.readValue(response.asString(), MRData.class);
			System.out.println(mrData.getSeries());
			System.out.println(mrData.getCircuitTable().getSeason());

			// assertions:
			Assert.assertNotNull(mrData);
			Assert.assertEquals(mrData.getSeries(), "f1");
			Assert.assertEquals(mrData.getCircuitTable().getSeason(), "2017");

			Circuit circuit = mrData.getCircuitTable().getCircuits().get(0);
			Assert.assertEquals(circuit.getCircuitName(), "Albert Park Grand Prix Circuit");
			Assert.assertEquals(circuit.getCircuitId(), "albert_park");
			Assert.assertEquals(circuit.getLocation().getLocality(), "Melbourne");
			Assert.assertEquals(circuit.getLocation().getCountry(), "Australia");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
