package com.gawari._himanshu.webapplication.springbootwebapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

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
	@RequestMapping("/login")
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
