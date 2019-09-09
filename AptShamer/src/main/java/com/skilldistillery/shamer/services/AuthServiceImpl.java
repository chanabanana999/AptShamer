package com.skilldistillery.shamer.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.skilldistillery.shamer.entities.Complex;
import com.skilldistillery.shamer.entities.User;
import com.skilldistillery.shamer.entities.UserProfile;
import com.skilldistillery.shamer.respositories.ComplexRepository;
import com.skilldistillery.shamer.respositories.UserRepository;

@Transactional
@Repository
public class AuthServiceImpl implements AuthService {
	  @Autowired
	  private PasswordEncoder encoder;
	  
	  @Autowired 
	  private UserRepository repo;
	  
	  @Autowired
	  private UserProfileService userSvc;
	  
	  @Autowired
	  private ComplexRepository cRepo;

	@Override
	public User register(User user) {
		String plainPassword = user.getPassword();
		String encryptedPassword = encoder.encode(plainPassword);
		user.setPassword(encryptedPassword);
		user.setEnabled(true);
		user.setRole("user");
		repo.saveAndFlush(user);
		UserProfile userP = new UserProfile();
		Complex complex = cRepo.findById(1).get();
		userP.setComplex(complex);
		userP.setUser(user);
		userSvc.create(userP);
		user.setProfile(userP);
		repo.saveAndFlush(user);
		return user;
	}
}