package com.gawari._himanshu.webapplication.springbootwebapplication.service;

import org.springframework.stereotype.Service;

//@Component
@Service
public class LoginService {

	public boolean validateUser(String user, String pass) {
		return user.equalsIgnoreCase("atsuko") && pass.equalsIgnoreCase("maeda");
	}
}
