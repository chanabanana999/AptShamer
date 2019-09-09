package com.skilldistillery.shamer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="complex_id")
	private Complex complex;
	
	private String name;
	private String phone;
	private String email;
	public Contact() {
		super();
	}
	public Contact(Complex complex, String name, String phone, String email) {
		super();
		this.complex = complex;
		this.name = name;
		this.phone = phone;
		this.email = email;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
