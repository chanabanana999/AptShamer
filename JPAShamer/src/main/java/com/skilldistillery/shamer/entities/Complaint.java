package com.skilldistillery.shamer.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="complex_id")
	private Complex complex;
	
	@ManyToOne
	@JoinColumn(name="user_profile_id")
	private UserProfile userProfile;
	
	@Column(name="created_date")
	@CreationTimestamp
	private Date created;
	
	@Column(name="resolved_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date resolved;
	
	private String title;
	
	private String description;
	
	@Column(name="is_resolved")
	private boolean isResolved;
	
	@Column(name="resolution_description")
	private String resolution;
	
	@OneToMany(mappedBy="complaint")
	private List<Image> images;

	@OneToMany(mappedBy="complaint")
	private List<Comment> comments;
	
	public void addImage(Image image) {
		if(images == null) { images = new ArrayList<>(); }
		if(!images.contains(image)) {
			images.add(image);
		}
		
	}
	
	public void removeImage(Image image) {
		if(images != null && images.contains(image)) {
			images.remove(image);
		}
	}
	
	public void addComment(Comment comment) {
		if(comments == null) { comments = new ArrayList<>(); }
		if(!comments.contains(comment)) {
			comments.add(comment);
		}
	}
	
	public void removeComment(Comment comment) {
		if(comments != null && comments.contains(comment)) {
			comments.remove(comment);
		}
	}
	
	
	
	public boolean isResolved() {
		return isResolved;
	}

	public void setIsResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Complex getComplex() {
		return complex;
	}

	public void setComplex(Complex complex) {
		this.complex = complex;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Complaint(int id, Complex complex, UserProfile userProfile, Date created, Date resolved, String title,
			String description, String resolution, List<Image> images, List<Comment> comments) {
		super();
		this.id = id;
		this.complex = complex;
		this.userProfile = userProfile;
		this.created = created;
		this.resolved = resolved;
		this.title = title;
		this.description = description;
		this.resolution = resolution;
		this.images = images;
		this.comments = comments;
		
		
	}

	public Complaint() {
		super();
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", created=" + created + ", resolved=" + resolved + ", title=" + title
				+ ", description=" + description + ", resolution=" + resolution + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complaint other = (Complaint) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
}
