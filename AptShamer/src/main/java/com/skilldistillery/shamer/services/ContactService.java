package com.skilldistillery.shamer.services;

import java.util.List;

import com.skilldistillery.shamer.entities.Contact;

public interface ContactService {
	List<Contact> index();
	Contact show(int id);
	Contact create(Contact contact);
	Contact update(int id, Contact contact);
	Boolean destroy(int id);
}
