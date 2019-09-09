package com.skilldistillery.shamer.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.shamer.entities.User;
import com.skilldistillery.shamer.respositories.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private UserRepository repo;

	@Override
	public List<User> getAllUsers(Principal principal) {
		User user = repo.findByUsername(principal.getName());
		if(user.getRole().equals("admin")) {
			List<User> allUsers = repo.findAll();
			for(int i = 0; i < allUsers.size(); i++) {
				if(allUsers.get(i).getRole().equals("admin")) {
					allUsers.remove(allUsers.get(i));
				}
			}
			return allUsers;
		} else {
			return null;
		}
	}

	@Override
	public User makeUserActive(int id, User reg, Principal principal) {
		User user = repo.findByUsername(principal.getName());
		if(user.getRole().equals("admin")) {
			User nonAdmin = repo.findById(id).get();
			nonAdmin.setEnabled(reg.getEnabled());
			repo.saveAndFlush(nonAdmin);
		}
		return repo.findById(id).get();
	}

	@Override
	public User findByName(String name, Principal principal) {
		User user = repo.findByUsername(principal.getName());
		if(user != null) {
			return repo.findByUsername(name);
			
		} else {
			return null;
		}
	}
	
}
