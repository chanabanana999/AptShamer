package com.skilldistillery.shamer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.shamer.entities.User;
import com.skilldistillery.shamer.entities.UserProfile;
import com.skilldistillery.shamer.respositories.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository uPRepo;

	@Override
	public List<UserProfile> index() {
		return uPRepo.findAll();
	}

	@Override
	public UserProfile show(int id) {
		Optional<UserProfile> opt = uPRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public UserProfile create(UserProfile user) {
		return uPRepo.saveAndFlush(user);
	}

	@Override
	public UserProfile update(int id, UserProfile user) {
		Optional<UserProfile> opt = uPRepo.findById(id);
		UserProfile managedUser = null;
		if (opt.isPresent()) {
			managedUser = opt.get();
			managedUser.setComplex(user.getComplex());
			managedUser.setEmail(user.getEmail());
			managedUser.setFirstName(user.getFirstName());
			managedUser.setLastName(user.getLastName());
			managedUser.setDisplayName(user.getDisplayName());
			managedUser.setImageUrl(user.getImageUrl());
			uPRepo.saveAndFlush(managedUser);
		}
		return managedUser;
	}

	@Override
	public Boolean destroy(int id) {
		Boolean deleted = false;
		try {
			uPRepo.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}

}
