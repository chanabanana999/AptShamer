package com.skilldistillery.shamer.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Complex {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="num_units")
	private int	numUnits;
	

	@OneToMany(mappedBy="complex")
	private List<Contact> contacts;
	
	@OneToMany(mappedBy="complex")
	private List<Complaint> complaints;
	
	@JsonIgnore
	@OneToMany(mappedBy="complex")
	private List<UserProfile> profiles;
	
	@JsonIgnore
	@OneToMany(mappedBy="complex")
	private List<Rating> ratings;
	
	// Add Rating
	public void addUserProfile(Rating rating) {
		if (ratings == null)
			ratings = new ArrayList<>();
		if (!ratings.contains(rating)) {
			ratings.add(rating);
		}
	}
	
	// Remove Rating
	public void removeComplaint(Rating rating) {
		rating.setComplex(null);
		if(ratings != null) {
			ratings.remove(rating);
		}
	}
	
	// Add UserProfile
	public void addUserProfile(UserProfile profile) {
		if (profiles == null)
			profiles = new ArrayList<>();
		if (!profiles.contains(profile)) {
			profiles.add(profile);
		}
	}
	
	// Remove UserProfile
	public void removeComplaint(UserProfile profile) {
		profile.setComplex(null);
		if(profiles != null) {
			profiles.remove(profile);
		}
	}
	
	// Add Complaint
	public void addComplaint(Complaint complaint) {
		if (complaints == null)
			complaints = new ArrayList<>();
		if (!complaints.contains(complaint)) {
			complaints.add(complaint);
		}
	}
	
	// Remove Complaint
	public void removeComplaint(Complaint complaint) {
		complaint.setComplex(null);
		if(complaints != null) {
			complaints.remove(complaint);
		}
	}
	
	// Add Contact
	public void addContact(Contact contact) {
		if (contacts == null)
			contacts = new ArrayList<>();
		if (!contacts.contains(contact)) {
			contacts.add(contact);
		}
	}
	
	// Remove Contact
	public void removeContact(Contact contact) {
		contact.setComplex(null);
		if(contacts != null) {
			contacts.remove(contact);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getNumUnits() {
		return numUnits;
	}

	public void setNumUnits(int numUnits) {
		this.numUnits = numUnits;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public List<UserProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
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
		Complex other = (Complex) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Complex() {
		super();
	}

	public Complex(int id, String name, String street, String city, String state, String zip, String imageUrl,
			int numUnits, List<Contact> contacts, List<Complaint> complaints, List<UserProfile> profiles,
			List<Rating> ratings) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.imageUrl = imageUrl;
		this.numUnits = numUnits;
		this.contacts = contacts;
		this.complaints = complaints;
		this.profiles = profiles;
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Complex [id=").append(id).append(", name=").append(name).append(", street=").append(street)
				.append(", city=").append(city).append(", state=").append(state).append(", zip=").append(zip)
				.append(", imageUrl=").append(imageUrl).append(", numUnits=").append(numUnits).append("]");
		return builder.toString();
	}
	
	
}
