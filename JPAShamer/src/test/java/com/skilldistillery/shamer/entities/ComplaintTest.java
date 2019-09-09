package com.skilldistillery.shamer.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComplaintTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Complaint complaint;

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
		complaint = em.find(Complaint.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		complaint = null;
	}


	@Test
	void test_User_entity_mapping() {
		assertEquals("Poop in the Elevator", complaint.getTitle());
	}
	
	@Test
	void test_User_Complaint_ManyToOne() {
		assertNotNull(complaint.getComments());
		assertEquals("testuser", complaint.getUserProfile().getDisplayName());
	}
	
	@Test
	void test_Compliant_Complex_ManyToOne() {
		assertNotNull(complaint.getComplex().getId());
		assertEquals("SomeApartment", complaint.getComplex().getName());
	}
}
