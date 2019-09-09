package com.skilldistillery.shamer.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.shamer.entities.Complex;
import com.skilldistillery.shamer.entities.User;
import com.skilldistillery.shamer.respositories.ComplexRepository;
import com.skilldistillery.shamer.respositories.UserRepository;

@Service
public class ComplexServiceImpl implements ComplexService {

	@Autowired
	private ComplexRepository repo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Override
	public List<Complex> index() {
		return repo.findAll();
	}
	
	@Override
	public Complex show(int id) {
		return repo.findById(id).get();
	}
	
	@Override
	public Complex create(Complex complex, Principal principal) {
		User user = uRepo.findByUsername(principal.getName());
		if(user != null) {
		return repo.saveAndFlush(complex);
		} else {
			return null;
		}
		
	}

	@Override
	public Complex update(int id, Complex complex) {
		Complex manPost = repo.findById(id).get();
		
		if(manPost != null) {
			manPost.setName(complex.getName());
			manPost.setStreet(complex.getStreet());
			manPost.setCity(complex.getCity());
			manPost.setState(complex.getState());
			manPost.setZip(complex.getZip());
			manPost.setImageUrl(complex.getImageUrl());
			manPost.setNumUnits(complex.getNumUnits());
			repo.saveAndFlush(manPost);
		}
		return manPost;
	}
	
	@Override
	public List<Complex> complexBySearch(String name) {
		name.split(", ");
		return repo.findByCityLikeOrStreetLike(name, name);
	}
	
	
}


















