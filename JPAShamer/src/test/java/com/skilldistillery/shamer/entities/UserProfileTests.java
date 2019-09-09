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

class UserProfileTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserProfile profile;

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
		profile = em.find(UserProfile.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		profile= null;
	}
	@Test
	void test_userprofile_mappings() {
		assertNotNull(profile);
		assertEquals("test@email.com", profile.getEmail());
		assertEquals("SomeApartment", profile.getComplex().getName());
		assertEquals("what a great place", profile.getRatings().get(0).getComment());
		assertEquals("Poop in the Elevator", profile.getComplaints().get(0).getTitle());
		assertEquals("at least it stopped smelling, now it's petrified", profile.getComments().get(0).getText());
	}

}
