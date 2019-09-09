package com.skilldistillery.shamer.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.shamer.entities.User;
import com.skilldistillery.shamer.services.AdminService;

@RestController
@CrossOrigin({ "*", "http://localhost:4203" })
@RequestMapping("api")
public class AdminController {
	
	@Autowired
	private AdminService svc;

	@GetMapping("admin")
	public List<User> getAllUsers(Principal principal) {
		return svc.getAllUsers(principal);
	}
	
	@PutMapping("admin/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user, Principal principal) {
		return svc.makeUserActive(id, user, principal);
	}
	
	@GetMapping("user/{name}")
	public User getUserByName(Principal principal, @PathVariable String name) {
		return svc.findByName(name, principal);
	}
}

