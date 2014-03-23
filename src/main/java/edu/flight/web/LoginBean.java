package edu.flight.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.springframework.context.annotation.Scope;

import edu.flight.service.SecurityOfficerService;

@Named
@Scope("session")
public class LoginBean {
	private String name;
	private String password;
	
	@Inject
	SecurityOfficerService securityOfficerService;

	public LoginBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String loginPage(){
		
		int role = 0;
		if (name!="" && password!=""){
			try {
		role = securityOfficerService.getRoleLogin(name, password);
			}
			catch (NoResultException e){
				 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login failed",  "Check your login and password.");  
		         FacesContext.getCurrentInstance().addMessage(null, message);
				}
		}
		switch(role){
		case 1: return "securityNavigator.xhtml";
		case 2: return "businessAdministrator.xhtml";
		case 3: return "accountantNavigator.xhtml";
		case 4: return "analystNavigator.xhtml";
		default : return "index.xhtml"; }
		}
	}
	
	

