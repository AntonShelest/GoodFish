package com.bionic.edu.beans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.User;
import com.bionic.edu.service.UserService;

@Named
@Scope("session")
public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;
	private User user = null;
	private String failed;
	
	public UserBean(){
		user = new User();
		failed = "false";
	}
	
	public String login(){
		user = userService.login(user);
		if (user.getId() != 0)
			return "Customer";
		else { 
			failed = "true";
			return "login";
		}
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getFailed() {
		return failed;
	}

	public void setFailed(String failed) {
		this.failed = failed;
	}
}
