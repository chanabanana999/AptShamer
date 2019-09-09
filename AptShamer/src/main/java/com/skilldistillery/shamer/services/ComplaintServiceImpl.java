package com.skilldistillery.shamer.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.shamer.entities.Complaint;
import com.skilldistillery.shamer.entities.Complex;
import com.skilldistillery.shamer.entities.User;
import com.skilldistillery.shamer.respositories.ComplaintRepository;
import com.skilldistillery.shamer.respositories.ComplexRepository;
import com.skilldistillery.shamer.respositories.UserProfileRepository;
import com.skilldistillery.shamer.respositories.UserRepository;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintRepository repo;

	@Autowired
	private ComplexRepository cRepo;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private UserProfileRepository userRepo;

	public List<Complaint> index(int id) {
		return repo.findByComplex_Id(id);
	}

	public Complaint show(int id, int cid) {
		Optional<Complex> complex = cRepo.findById(id);
		if (complex != null) {
			return repo.findById(cid).get();
		} else {
			return null;
		}
	}

	public Complaint create(int id, Complaint complaint, Principal principal) {
		User user = uRepo.findByUsername(principal.getName());
		if (user != null) {
			complaint.setUserProfile(user.getProfile());
			complaint.setComplex(cRepo.findById(id).get());
			complaint.setIsResolved(false);
			return repo.saveAndFlush(complaint);
		}
		return complaint;
	}

	public Complaint update(int cid, Complaint complaint, Principal principal) {
		User user = uRepo.findByUsername(principal.getName());
		Complaint newComplaint = repo.findById(cid).get();
		if (user.getProfile().getId() == newComplaint.getUserProfile().getId()) {
			if (newComplaint != null) {
				//newComplaint.setComplex(complaint.getComplex());
				newComplaint.setCreated(complaint.getCreated());
				newComplaint.setDescription(complaint.getDescription());
				newComplaint.setResolution(complaint.getResolution());
				newComplaint.setIsResolved(complaint.isResolved());
				newComplaint.setResolved(complaint.getResolved());
				newComplaint.setTitle(complaint.getTitle());
				repo.saveAndFlush(newComplaint);
			}
		}
		return newComplaint;
	}

	public Boolean destroy(int id, Principal principal) {
		Complaint complaint = repo.findById(id).get();
		User user = uRepo.findByUsername(principal.getName());
		boolean deleted = false;
		if (user.getProfile().getId() == complaint.getUserProfile().getId()) {
			if (complaint != null) {
				repo.deleteById(id);
				deleted = true;
			}
		}
		return deleted;
	}

	@Override
	public List<Complaint> userComplaints(int id, Principal principal) {
		User user = uRepo.findByUsername(principal.getName());
		if(user.getRole().equals("admin")) {
			List<Complaint> userComplaints = repo.findAllByUserProfileId(id);
			return userComplaints;
		}
		
		return null;
	}

}
