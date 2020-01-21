package com.gawari._himanshu.webapplication.springbootwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gawari._himanshu.webapplication.springbootwebapplication.service.LoginService;

@Controller
@SessionAttributes(value = "name")
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {
		boolean isValid = loginService.validateUser(name, password);
		if (!isValid) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		model.put("name", name);
		model.put("password", password);
		return "welcome";
	}

	
	
	
	/* Testng Code */

	@RequestMapping("/login1")
	// @ResponseBody
	public String loginMessage() {
		return "login";
	}

	// http://localhost:8080/login2?name=himanshu
	@RequestMapping("/login2")
	public String loginMessage(@RequestParam String name, ModelMap model) {
		System.out.println(name);
		// Model --> only addAttribute
		// ModelMap --> addAttribute or put both

		// model.put("name", name);
		model.addAttribute("name", name);
		return "login";
	}

	// http://localhost:8080/login?name=himanshu
	@RequestMapping("/login3")
	public String loginMessage(@RequestParam String name, Model model) {
		System.out.println(name);
		model.addAttribute("name", name);
		return "login";
	}

	@RequestMapping({ "", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping("/oups")
	public String oupsHandler() {
		return "notImplemented";
	}

}
