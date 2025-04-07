package LombokWithJSONArray;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private List<UserData> data;
	private Support support;
	
	

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UserData {
		private int id;
		private String email;
		private String first_name;
		private String last_name;
		private String avatar;		
	}

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Support {
		private String url;
		private String text;
	}

}
