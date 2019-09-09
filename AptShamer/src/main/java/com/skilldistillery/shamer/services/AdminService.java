package com.skilldistillery.shamer.services;

import java.security.Principal;
import java.util.List;

import com.skilldistillery.shamer.entities.User;

public interface AdminService {
	
	List<User> getAllUsers(Principal principals);
	
	User makeUserActive(int id, User user, Principal principal);

	User findByName(String name, Principal principal);

}
