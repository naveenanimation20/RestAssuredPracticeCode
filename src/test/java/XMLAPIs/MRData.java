package XMLAPIs;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "MRData", namespace = "http://ergast.com/mrd/1.5")
public class MRData {

	@JacksonXmlProperty(isAttribute = true)
	private String series;

	@JacksonXmlProperty(isAttribute = true)
	private String url;

	@JacksonXmlProperty(isAttribute = true)
	private int limit;

	@JacksonXmlProperty(isAttribute = true)
	private int offset;

	@JacksonXmlProperty(isAttribute = true)
	private int total;

	@JacksonXmlProperty(localName = "CircuitTable")
	private CircuitTable circuitTable;

	@Data
	public static class CircuitTable {

		@JacksonXmlProperty(isAttribute = true)
		private String season;

		@JacksonXmlElementWrapper(useWrapping = false)
		@JacksonXmlProperty(localName = "Circuit")
		private List<Circuit> circuits;
	}

	@Data
	public static class Circuit {

		@JacksonXmlProperty(isAttribute = true)
		private String circuitId;

		@JacksonXmlProperty(isAttribute = true)
		private String url;

		@JacksonXmlProperty(localName = "CircuitName")
		private String circuitName;

		@JacksonXmlProperty(localName = "Location")
		private Location location;
	}

	@Data
	public static class Location {

		@JacksonXmlProperty(isAttribute = true, localName = "lat")
		private double latitude;

		@JacksonXmlProperty(isAttribute = true, localName = "long")
		private double longitude;

		@JacksonXmlProperty(localName = "Locality")
		private String locality;

		@JacksonXmlProperty(localName = "Country")
		private String country;
	}
}

