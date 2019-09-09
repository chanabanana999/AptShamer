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

class RatingTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Rating rating;

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
		rating = em.find(Rating.class, 1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		rating = null;
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void test_rating_has_id() {
		assertNotNull(rating.getId());
		assertEquals(1, rating.getId());
	}
	
	@Test
	void test_rating_has_profile_id() {
		assertNotNull(rating.getUserProfile().getId());
		assertEquals(1, rating.getUserProfile().getId());
	}
	
	@Test
	void test_rating_has_complex_id() {
		assertNotNull(rating.getComplex().getId());
		assertEquals(1, rating.getComplex().getId());
	}
	
	@Test
	void test_rating_has_comment() {
		assertNotNull(rating.getComment());
		assertEquals("what a great place", rating.getComment());
	}
	
	@Test 
	void test_rating_has_update_date() {
		assertNotNull(rating.getUpdate());
		
	}
	
	@Test
	void test_rating_has_rating() {
		assertNotNull(rating.getRating());
		assertEquals(5, rating.getRating());
	}
}
