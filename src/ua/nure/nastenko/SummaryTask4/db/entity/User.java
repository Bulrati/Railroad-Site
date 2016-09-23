package ua.nure.nastenko.SummaryTask4.db.entity;


public class User {

	private String login;
	private String password;
	private String first_name;
	private String last_name;
	private String role;

	public User(String login, String password, String first_name,
			String last_name, String role) {
		this.login = login;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password
				+ ", first_name=" + first_name + ", last_name=" + last_name
				+ ", role=" + role + "]";
	}

	
	
	

}