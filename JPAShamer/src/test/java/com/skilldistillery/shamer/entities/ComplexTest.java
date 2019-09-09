package com.skilldistillery.shamer.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComplexTest {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShamerPU");
	private EntityManager em;
	private Complex complex;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		complex = em.find(Complex.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		complex = null;
	}

	@Test
	void test_Complex_mapping() {
		assertNotNull(complex);
		assertTrue(complex.getComplaints().size() > 0);
		assertTrue(complex.getRatings().size() > 0);
		assertTrue(complex.getContacts().size() > 0);
		assertTrue(complex.getProfiles().size() > 0);
		assertEquals("123 Main", complex.getStreet());
		assertEquals("This is gross.It's been here 3 days", complex.getComplaints().get(0).getDescription());
		assertEquals("Manager", complex.getContacts().get(0).getName());
		assertEquals("testuser", complex.getProfiles().get(0).getDisplayName());
	}

}
