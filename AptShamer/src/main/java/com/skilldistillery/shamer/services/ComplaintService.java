package com.skilldistillery.shamer.services;

import java.security.Principal;
import java.util.List;

import com.skilldistillery.shamer.entities.Complaint;

public interface ComplaintService {

	public List<Complaint> index(int id);
	
	public Complaint show(int id, int cid);
	
	public Complaint create(int id, Complaint complaint, Principal principal);
	
	public Complaint update(int cid, Complaint complaint, Principal principal);
	
	public Boolean destroy(int id, Principal principal);
	
	public List<Complaint> userComplaints(int id, Principal  principal);
}
