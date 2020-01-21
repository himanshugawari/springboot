package com.gawari._himanshu.webapplication.springbootwebapplication.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gawari._himanshu.webapplication.springbootwebapplication.model.Todo;
import com.gawari._himanshu.webapplication.springbootwebapplication.service.TodoService;

@Controller
@SessionAttributes(value = "name")
public class TodoController {

	@Autowired
	TodoService todoService;

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		String name = (String) model.get("name");
		model.addAttribute("todos", todoService.retrieveTodos(name));
		// model.put("todos", todoService.retrieveTodos(name));
		return "list-todos";
	}

	// using @RequestParam i.e String
	/*
	 * @RequestMapping(value="/add-todo", method = RequestMethod.GET) public String
	 * showAddTodoPage(ModelMap model){ return "todo"; }
	 */

	// using Command Bean i.e Todo todo
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		//model.addAttribute("todo", new Todo(0, (String) model.get("name"), "Default", new Date(), false));
		return "todo";
	}

	// using @RequestParam i.e String
	/*
	 * @RequestMapping(value="/add-todo", method = RequestMethod.POST) public String
	 * addTodo(ModelMap model, @RequestParam String desc){
	 * todoService.addTodo((String) model.get("name"), desc, new Date(), false);
	 * return "redirect:/list-todos"; }
	 */

	// using Command Bean i.e Todo todo
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			model.put("errorMessage", result.getFieldError("desc"));
			return "todo";
		}
		todoService.addTodo((String) model.get("name"), todo.getDesc(), new Date(), false);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			model.put("errorMessage", result.getFieldError("desc"));
			return "todo";
		}
		
		todo.setUser((String) model.get("name"));
		
		todoService.updateTodo(todo);

		return "redirect:/list-todos";
	}

}
