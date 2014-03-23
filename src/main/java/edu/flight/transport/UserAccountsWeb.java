package edu.flight.transport;

public class UserAccountsWeb {
	private int code;
	private String login;
	private String mail;
	private String role;
	
	public UserAccountsWeb(int code, String login, String mail, String role) {
		this.code = code;
		this.login = login;
		this.mail = mail;
		this.role = role;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
