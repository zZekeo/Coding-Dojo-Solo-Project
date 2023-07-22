package com.codingdojo.ToDo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.ToDo.models.ToDo;
import com.codingdojo.ToDo.services.ToDoService;
import com.codingdojo.ToDo.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ToDoController {
	@Autowired
	ToDoService toDoService;
	
	@Autowired
	UserService userService;
	
	// ---------- READ ALL ----------
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		// if there is no user_id in session, then the user is not logged in
		// and should not be able to view the home page
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		// if the user is logged in, get all the todos
		// and send them to the jsp to be rendered
		model.addAttribute("allToDos", toDoService.getAll());
		
		// get the currently logged in user's id
		Long userID = (Long) session.getAttribute("user_id");
		
		// pass the logged in user information to the dashboard
		model.addAttribute("loggedUser", userService.getOneUser(userID));
		
		return "/dashboard.jsp";
	}
	
	// ---------- CREATE ----------
	@GetMapping("/todos/new")
	public String newToDo(
			@ModelAttribute("toDoObj") ToDo emptyToDo,
			HttpSession session) {
		// if there is no user_id in session, then the user is not logged in
		// and should not be able to create a new todo
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		return "/todos/new.jsp";
	}
	
	// ---------- PROCESS CREATE ----------
	@PostMapping("/todos/new")
	public String processToDo(
			@Valid @ModelAttribute("toDoObj") ToDo filledToDo,
			BindingResult results) {
		// if validations failed redirect and show errors
		if (results.hasErrors()) {
			return "/todos/new.jsp";
		}
		
		ToDo newToDo = toDoService.create(filledToDo);
		
		return "redirect:/dashboard";
	}
	
	// ---------- READ ONE ----------
	@GetMapping("/todos/{id}")
	public String oneToDo(
			@PathVariable("id") Long id, Model model, HttpSession session) {
		// if there is no user_id in session, then the user is not logged in
		// and should not be able to view one of the todos
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		model.addAttribute("oneToDo", toDoService.getOne(id));
		
		return "/todos/show.jsp";
	}
	
	// ---------- UPDATE ----------
	@GetMapping("/todos/{id}/edit")
	public String edit(
			@PathVariable("id") Long id, Model model, HttpSession session) {
		// if there is no user_id in session, then the user is not logged in
		// and should not be able to view one of the todos
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		model.addAttribute("toDoObj", toDoService.getOne(id));
		
		return "/todos/edit.jsp";
	}
	
	// ---------- PROCESS UPDATE ----------
	@PutMapping("/todos/{id}/edit")
	public String update(
			@Valid @ModelAttribute("toDoObj") ToDo filledToDo,
			BindingResult results) {
		// if validations failed redirect and show errors
		if (results.hasErrors()) {
			return "/todos/edit.jsp";
		}
		
		toDoService.updateToDo(filledToDo);
		
		return "redirect:/dashboard";
	}
	
	// ---------- PROCESS DELETE ----------
	@DeleteMapping("/todos/{id}/delete")
	public String delete(@PathVariable("id") Long toDoId) {
		toDoService.deleteToDo(toDoId);
		
		return "redirect:/dashboard";
	}
}