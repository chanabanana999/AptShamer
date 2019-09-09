package com.skilldistillery.shamer.services;

import java.util.List;

import com.skilldistillery.shamer.entities.User;
import com.skilldistillery.shamer.entities.UserProfile;

public interface UserProfileService {
	List<UserProfile> index();
	UserProfile show(int id);
	UserProfile create(UserProfile user);
	UserProfile update(int id, UserProfile user);
	Boolean destroy(int id);
}
