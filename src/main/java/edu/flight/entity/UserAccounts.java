package edu.flight.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserAccounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userID;
	String login;
	String password;
	String mail;
	@ManyToOne
	@JoinColumn(name = "role")
	Roles role;

	public UserAccounts() {
	}

	public UserAccounts(int userID, String login, String password, String mail,
			Roles role) {
		this.userID = userID;
		this.login = login;
		this.password = password;
		this.mail = mail;
		this.role = role;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
}
