package DynamicJsonCreation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CompanyJsonGenerator {
	private static final Random random = new Random();

	public static void main(String[] args) {
		CompanyData companyData = new CompanyData();

		// 1. Create location
		CompanyData.Location location = new CompanyData.Location();
		location.setCity("San Francisco");
		location.setState("CA");
		location.setCountry("USA");

		// 2. Create departments
		List<CompanyData.Department> departments = new ArrayList<>();
		departments.add(createDepartment(1, "Engineering", 4)); // 4 employees
		departments.add(createDepartment(2, "Marketing", 2)); // 2 employees
		departments.add(createDepartment(3, "HR", 3)); // 3 employees

		// Now adding company data
		CompanyData.Company company = new CompanyData.Company();
		company.setName("Tech Solutions Ltd");
		company.setLocation(location);
		company.setDepartments(departments);

		// Adding company and metadata
		companyData.setCompany(company);
		CompanyData.Metadata metadata = new CompanyData.Metadata();
		metadata.setVersion(1.2);
		metadata.setSource("internal");
		companyData.setMetadata(metadata);

		// Convert Company POJO to JSON using Jackson
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Generate JSON file
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("company.json"), companyData);
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(companyData));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	private static CompanyData.Department createDepartment(int id, String name, int employeeCount) {
		CompanyData.Department department = new CompanyData.Department();
		department.setId(id);
		department.setName(name);
		department.setEmployees(createEmployees(employeeCount));
		return department;
	}

	private static List<CompanyData.Employee> createEmployees(int count) {
		List<CompanyData.Employee> employees = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			employees.add(createEmployee(100 + i, "Employee " + (100 + i), randomRole(), generateRandomSkills(),
					generateRandomProjects(random.nextInt(3) + 1)));
		}
		return employees;
	}

	private static CompanyData.Employee createEmployee(int id, String name, String role, List<String> skills,
			List<CompanyData.Project> projects) {
		CompanyData.Employee employee = new CompanyData.Employee();
		employee.setId(id);
		employee.setName(name);
		employee.setRole(role);
		employee.setSkills(skills);
		employee.setProjects(projects);
		return employee;
	}

	private static List<CompanyData.Project> generateRandomProjects(int count) {
		List<CompanyData.Project> projects = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			projects.add(createProject("P00" + (i + 1), "Project " + (i + 1), randomStatus(), generateRandomTasks(random.nextInt(5) + 1)));
		}
		return projects;
	}

	private static List<CompanyData.Task> generateRandomTasks(int count) {
		List<CompanyData.Task> tasks = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			tasks.add(createTask("T00" + (i + 1), "Task " + (i + 1), random.nextBoolean()));
		}
		return tasks;
	}

	private static CompanyData.Project createProject(String projectId, String name, String status,
			List<CompanyData.Task> tasks) {
		CompanyData.Project project = new CompanyData.Project();
		project.setProjectId(projectId);
		project.setName(name);
		project.setStatus(status);
		project.setTasks(tasks);
		return project;
	}

	private static CompanyData.Task createTask(String taskId, String description, boolean completed) {
		CompanyData.Task task = new CompanyData.Task();
		task.setTaskId(taskId);
		task.setDescription(description);
		task.setCompleted(completed);
		return task;
	}

	private static List<String> generateRandomSkills() {
		List<String> skills = Arrays.asList("JavaScript", "React", "Node.js", "Selenium", "Python", "SQL");
		List<String> randomSkills = new ArrayList<>();
		for (int i = 0; i < random.nextInt(3) + 1; i++) {
			randomSkills.add(skills.get(random.nextInt(skills.size())));
		}
		return randomSkills;
	}

	private static String randomRole() {
		List<String> roles = Arrays.asList("Software Engineer", "QA Engineer", "Marketing Manager", "HR Specialist");
		return roles.get(random.nextInt(roles.size()));
	}

	private static String randomStatus() {
		List<String> statuses = Arrays.asList("In Progress", "Completed", "Pending");
		return statuses.get(random.nextInt(statuses.size()));
	}
}
