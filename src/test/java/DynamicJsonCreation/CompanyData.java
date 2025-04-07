package DynamicJsonCreation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyData {
	private Company company;
	private Metadata metadata;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Company {
		private String name;
		private Location location;
		private List<Department> departments;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Location {
		private String city;
		private String state;
		private String country;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Department {
		private int id;
		private String name;
		private List<Employee> employees;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Employee {
		private int id;
		private String name;
		private String role;
		private List<String> skills;
		private List<Project> projects;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Project {
		private String projectId;
		private String name;
		private String status;
		private List<Task> tasks;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Task {
		private String taskId;
		private String description;
		private boolean completed;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Metadata {
		private double version;
		private String source;
	}
}
