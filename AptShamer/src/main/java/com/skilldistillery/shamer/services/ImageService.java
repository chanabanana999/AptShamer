package com.skilldistillery.shamer.services;

import java.util.List;

import com.skilldistillery.shamer.entities.Image;

public interface ImageService {
	
	public List<Image> index(int id);
	
	public Image show(int mid);
	
	public Image create(int id, Image image);
	
	public Boolean destroy(int id);

}
