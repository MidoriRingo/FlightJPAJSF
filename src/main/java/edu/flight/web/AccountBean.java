package edu.flight.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;

import edu.flight.entity.UserAccounts;
import edu.flight.service.SecurityOfficerService;
import edu.flight.transport.UserAccountsWeb;

@Named
@Scope("session")
public class AccountBean {
	
	@Inject
	private SecurityOfficerService securityOfficerService;
	
	private String login;
	private String email;
	private String role;
	private List<UserAccounts> list;
	private UserAccounts selectedAccount;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public UserAccounts getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(UserAccounts selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public List<UserAccounts> getList() {
		return list;
	}
	
	public String createUser(){
		securityOfficerService.createUser(login, email, role);
		return "securityNavigator.xhtml";
	}
	
	public List<UserAccountsWeb> getAllUserAccounts() {
		return securityOfficerService.getUserAccounts();
		
	}
	
	public void onEdit(RowEditEvent event) {
		securityOfficerService.editUser((UserAccountsWeb) event.getObject());
		FacesMessage msg = new FacesMessage("Account Edited",
				((UserAccountsWeb) event.getObject()).getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}

	public void onCancel(RowEditEvent event) {	
		FacesMessage msg = new FacesMessage("Account Editing Cancelled",
				((UserAccountsWeb) event.getObject()).getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void removeUser(UserAccountsWeb user){
		securityOfficerService.deleteUser(user);
	}
	
	
}
