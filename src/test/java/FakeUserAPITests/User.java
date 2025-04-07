package FakeUserAPITests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String email;
	private String username;
	private String password;
	private String phone;
	private Name name;
	private Address address;
	

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Name {
		private String firstname;
		private String lastname;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Address {
		private String city;
		private String street;
		private int number;
		private String zipcode;
		private GeoLocation geoLocation;

		@Data
		@Builder
		@NoArgsConstructor
		@AllArgsConstructor
		public static class GeoLocation {
			private String lat;
			@JsonProperty("long")
			private String longitude;
		}

	}

}
