package com.gawari._himanshu.springbootsimpleapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

	@Value("${welcome.message}")
	private String welcomeMessage;

	public String retrieveWelcomeMessageFromPropertiesFile() {
		return welcomeMessage;
	}

	public String retrieveWelcomeMessage() {
		return "Good Morning!!!!!!!!!!!";
	}

}
