package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entities.Users;
import com.example.demo.service.UsersService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService service;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users users){
		boolean userStatus=service.emailExits(users.getEmail());
		if(userStatus==false) {
			service.addUsers(users);
			System.out.println("User added");
		}else{
			System.out.println("User already exists");
		}
		return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,
							@RequestParam("password")String password,
							HttpSession session) {
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "admin";
			}else {
				return "customer";
			}
		}else {
			return "login";
		}
	}
//	@GetMapping("/pay")
//	public String pay(@RequestParam String email) {
//		boolean paymentStatus=false;//payment api
//		if(paymentStatus==true) {
//			Users user=service.getUser(email);
//			user.setPremium(true);
//			service.updateUsers(user);
//		}
//		return "login";
//	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
