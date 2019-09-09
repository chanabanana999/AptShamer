package com.skilldistillery.shamer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.shamer.entities.Contact;
import com.skilldistillery.shamer.entities.UserProfile;
import com.skilldistillery.shamer.respositories.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository cRepo;
	
	@Override
	public List<Contact> index() {
		return cRepo.findAll();
	}

	@Override
	public Contact show(int id) {
		Optional<Contact> opt = cRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Contact create(Contact contact) {
		return cRepo.saveAndFlush(contact);
	}
	
	@Override
	public Contact update(int id, Contact contact) {
		Optional<Contact> opt = cRepo.findById(id);
		Contact managedContact = null;
		if (opt.isPresent()) {
			managedContact = opt.get();
			managedContact.setComplex(contact.getComplex());
			managedContact.setName(contact.getName());
			managedContact.setPhone(contact.getPhone());
			managedContact.setEmail(contact.getEmail());
			cRepo.saveAndFlush(managedContact);
		}
		return managedContact;
	}

	@Override
	public Boolean destroy(int id) {
		Boolean deleted = false;
		try {
			cRepo.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}

}
