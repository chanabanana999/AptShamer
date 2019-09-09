package com.skilldistillery.shamer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.shamer.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
