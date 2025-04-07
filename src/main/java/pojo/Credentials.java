package pojo;

//pojo: plain old java object
//can not extent/implement anything
//private data fields(variables)
//public getter/setter
//Encapsulation
//public const...

public class Credentials {

	private String username;
	private String password;

	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// getter and setter:
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
