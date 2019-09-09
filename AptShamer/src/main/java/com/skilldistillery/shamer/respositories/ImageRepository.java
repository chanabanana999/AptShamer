package com.skilldistillery.shamer.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.shamer.entities.Image;

public interface ImageRepository extends JpaRepository <Image, Integer>{

	List<Image> findByComplaint_Id(int id);

}
