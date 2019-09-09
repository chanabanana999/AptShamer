package com.skilldistillery.shamer.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String text;
	
	@CreationTimestamp
	@Column(name="comment_date")
	private Date commentDate;
	
	private int vote;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="complaint_id")
	private Complaint complaint;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_profile_id")
	private UserProfile userProfile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
}
