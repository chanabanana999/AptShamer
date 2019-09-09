package com.skilldistillery.shamer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.shamer.entities.Complaint;
import com.skilldistillery.shamer.entities.Image;
import com.skilldistillery.shamer.respositories.ComplaintRepository;
import com.skilldistillery.shamer.respositories.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository repo;
	
	@Autowired
	private ComplaintRepository cRepo;
	
	
	public List<Image> index(int id){
		return repo.findByComplaint_Id(id);
	}
	
	public Image show(int id) {
		return repo.findById(id).get();
	}
	
	public Image create(int id, Image image) {
		image.setComplaint(cRepo.findById(id).get());
		return repo.saveAndFlush(image);
	}
	
	public Boolean destroy(int id) {
		Image image = repo.findById(id).get();
		boolean deleted = false;
		if(image != null) {
			repo.deleteById(id);
			deleted = true;
		}
		return deleted;
	}

}
