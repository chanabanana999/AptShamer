package com.skilldistillery.shamer.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="user_complex_rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_profile_id")
	private UserProfile userProfile;
	
	@ManyToOne
	@JoinColumn(name="complex_id")
	private Complex complex;
	
	private String comment;
	
	@Column(name="last_update")
	@UpdateTimestamp
	private Date update;
	
	private int rating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Complex getComplex() {
		return complex;
	}

	public void setComplex(Complex complex) {
		this.complex = complex;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Rating(int id, UserProfile userProfile, Complex complex, String comment, Date update, int rating) {
		super();
		this.id = id;
		this.userProfile = userProfile;
		this.complex = complex;
		this.comment = comment;
		this.update = update;
		this.rating = rating;
	}

	public Rating() {
		super();
	}
	
	
}
