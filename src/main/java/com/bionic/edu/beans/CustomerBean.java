package com.bionic.edu.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.User;
import com.bionic.edu.service.CustomerService;

@Named("customer")
@Scope("session")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBean currentUser;
	private User user = null;
	
	@Inject
	private CustomerService customerService;
	
	public CustomerBean() { }
	
	@PostConstruct
	public void init(){
		user = currentUser.getUser();
	}
	
	public UserBean getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserBean currentUser) {
		this.currentUser = currentUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
