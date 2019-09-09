package com.skilldistillery.shamer.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.shamer.entities.UserProfile;
import com.skilldistillery.shamer.services.UserProfileService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4203" })
public class UserProfileController {

	@Autowired
	private UserProfileService uPSvc;

	@GetMapping(path = "users/ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("users")
	public List<UserProfile> showUsers() {
		return uPSvc.index();
	}

	@GetMapping("users/{id}")
	public UserProfile showUser(@PathVariable int id, HttpServletResponse resp) {
		UserProfile user = uPSvc.show(id);
		if (user == null) {
			resp.setStatus(404);
		}
		return user;
	}

	@PostMapping("users")
	public UserProfile createUserProfile(@RequestBody UserProfile user, HttpServletRequest req,
			HttpServletResponse resp) {
		UserProfile newUser = uPSvc.create(user);
		resp.setStatus(201);
		StringBuffer sb = req.getRequestURL();
		sb.append("/");
		sb.append(user.getId());
		resp.setHeader("Location", sb.toString());
		if (newUser == null) {
			resp.setStatus(404);
		}
		return newUser;
	}

	@PutMapping("users/{id}")
	public UserProfile updateUser(@PathVariable int id, @RequestBody UserProfile user, HttpServletRequest req,
			HttpServletResponse resp) {
		UserProfile updatedUser;
		try {
			updatedUser = uPSvc.update(id, user);
			resp.setStatus(200);
			StringBuffer url = req.getRequestURL();
			String newPostUrl = url.toString();
			resp.addHeader("Location", newPostUrl);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			updatedUser = null;
		}
		return updatedUser;
	}

	@DeleteMapping("users/{id}")
	public Boolean deleteUser(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			uPSvc.destroy(id);
			resp.setStatus(202);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			return false;
		}
		return true;
	}

}