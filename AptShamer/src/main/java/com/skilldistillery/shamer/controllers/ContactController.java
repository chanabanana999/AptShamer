package com.skilldistillery.shamer.controllers;

import java.security.Principal;
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

import com.skilldistillery.shamer.entities.Contact;
import com.skilldistillery.shamer.entities.UserProfile;
import com.skilldistillery.shamer.services.ContactService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4203" })
public class ContactController {
	
	@Autowired
	private ContactService cSvc;
	
	@GetMapping(path = "contacts/ping")
	public String ping() {
		return "pong";
	}
	
	@GetMapping("contacts")
	public List<Contact> showContacts() {
		return cSvc.index();
	}
	
	@GetMapping("contacts/{id}")
	public Contact showContact(@PathVariable int id, HttpServletResponse resp) {
		Contact contact = cSvc.show(id);
		if (contact == null) {
			resp.setStatus(404);
		}
		return contact;
	}

	@PostMapping("contacts")
	public Contact createContact(@RequestBody Contact contact, HttpServletRequest req,
			HttpServletResponse resp) {
		Contact newContact = cSvc.create(contact);
		resp.setStatus(201);
		StringBuffer sb = req.getRequestURL();
		sb.append("/");
		sb.append(contact.getId());
		resp.setHeader("Location", sb.toString());
		if (newContact == null) {
			resp.setStatus(404);
		}
		return newContact;
	}

	@PutMapping("contacts/{id}")
	public Contact updateContact(@PathVariable int id, @RequestBody Contact contact, HttpServletRequest req,
			HttpServletResponse resp) {
		Contact updatedContact;
		try {
			updatedContact = cSvc.update(id, contact);
			resp.setStatus(200);
			StringBuffer url = req.getRequestURL();
			String newPostUrl = url.toString();
			resp.addHeader("Location", newPostUrl);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			updatedContact = null;
		}
		return updatedContact;
	}

	@DeleteMapping("contacts/{id}")
	public Boolean deleteContact(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			cSvc.destroy(id);
			resp.setStatus(202);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			return false;
		}
		return true;
	}


}
