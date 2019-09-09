package com.skilldistillery.shamer.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ContactTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Contact contact;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("ShamerPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception { 
		em = emf.createEntityManager();
		contact = em.find(Contact.class, 1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		contact = null;
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void test_contact_has_id() {
		assertNotNull(contact.getId());
		assertEquals(1, contact.getId());
	}
	
	@Test
	void test_contact_has_name() {
		assertNotNull(contact.getName());
		assertEquals("Manager", contact.getName());
	}
	
	@Test
	void test_contact_has_phone() {
		assertNotNull(contact.getPhone());
		assertEquals("312-555-5555", contact.getPhone());
	}
	
	@Test
	void test_contact_has_complex() {
		assertNotNull(contact.getComplex());
		assertEquals(1, contact.getComplex().getId());
	}
	
	@Test
	void test_contact_has_email() {
		assertNotNull(contact.getEmail());
		assertEquals("manager@apartment.com", contact.getEmail());
	}
}
