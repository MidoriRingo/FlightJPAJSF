package edu.flight.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.flight.dao.PasswordChecker;
import edu.flight.dao.UserAccountsDAO;
import edu.flight.entity.UserAccounts;
import edu.flight.transport.UserAccountsWeb;

@Named
@Transactional
public class SecurityOfficerService {
	@Inject
	private UserAccountsDAO userAccountsDAO;

	public void createUser(String login, String email, String role) {
		UserAccounts user = new UserAccounts();
		user.setLogin(login);
		user.setMail(email);
		user.setRole(userAccountsDAO.getRole(role));
		user.setPassword(PasswordChecker.formPass());
		userAccountsDAO.create(user);
		System.out.println(user.getLogin());
	}

	public void deleteUser(UserAccountsWeb user) {
		userAccountsDAO.delete(user.getCode());
	}

	public void editUser(UserAccountsWeb user) {

		userAccountsDAO.updateUser(user);
	}
	
	public List<UserAccountsWeb> getUserAccounts(){
		return userAccountsDAO.findAll();
	}

	public int getRoleLogin(String name, String password) {
		return userAccountsDAO.getRoleLogin(name, password);
		
	}
}
