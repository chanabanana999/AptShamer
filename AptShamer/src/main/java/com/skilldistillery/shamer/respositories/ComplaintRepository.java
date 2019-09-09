package com.skilldistillery.shamer.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.shamer.entities.Complaint;

public interface ComplaintRepository extends JpaRepository <Complaint, Integer>{

	List<Complaint> findByComplex_Id(int id);

	List<Complaint> findAllByUserProfileId(int id);
}
