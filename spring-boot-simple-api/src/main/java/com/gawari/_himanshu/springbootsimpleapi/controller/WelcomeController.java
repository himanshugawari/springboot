package com.gawari._himanshu.springbootsimpleapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gawari._himanshu.springbootsimpleapi.configuration.BasicConfiguration;
import com.gawari._himanshu.springbootsimpleapi.service.WelcomeService;

@RestController
//@Controller
public class WelcomeController {

	@Autowired
	private WelcomeService welcomeService;

	@Autowired
	private BasicConfiguration basicConfiguration;

	@RequestMapping("/welcome")
	// @ResponseBody not needed with @RestController
	public String welcome() {

		if (welcomeService.retrieveWelcomeMessage() != null)
			return welcomeService.retrieveWelcomeMessage();

		return "Welcome to the home page!!!!!!!!!!";

	}

	@RequestMapping("/welcome1")
	// @ResponseBody not needed with @RestController
	public String welcomeFromPropertiesFile() {

		if (welcomeService.retrieveWelcomeMessageFromPropertiesFile() != null)
			return welcomeService.retrieveWelcomeMessageFromPropertiesFile();

		return "Welcome to the home page!!!!!!!!!!";

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfiguration() {
		Map map = new HashMap();
		map.put("message", basicConfiguration.getMessage());
		map.put("number", basicConfiguration.getNumber());
		map.put("value", basicConfiguration.isValue());

		return map;
	}

}
