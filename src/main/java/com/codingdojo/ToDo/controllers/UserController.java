package com.codingdojo.ToDo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.ToDo.models.LoginUser;
import com.codingdojo.ToDo.models.User;
import com.codingdojo.ToDo.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index(
			@ModelAttribute("newUser") User emptyUser,
			@ModelAttribute("newLogin") LoginUser emptyLoginUser) {
		return "index.jsp";
	}
	
	// PROCESS REGISTER
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User filledUser, 
			BindingResult results,
			HttpSession session,
			Model model) {
		
		User createdUser = userService.register(filledUser, results);
		
		if (results.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			
			return "index.jsp";
		}
		
		// save the users id in session
		session.setAttribute("user_id", createdUser.getId());
		
		return "redirect:/dashboard";
	}
	
	// PROCESS LOGIN
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin") LoginUser filledLoginUser, 
			BindingResult results,
			HttpSession session,
			Model model) {
		
		User loggedUser = userService.login(filledLoginUser, results);
		
		if (results.hasErrors()) {
			model.addAttribute("newUser", new User());
			
			return "index.jsp";
		}
		
		// save the logged users id in session
		session.setAttribute("user_id", loggedUser.getId());
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user_id");
		
		return "redirect:/";
	}
}