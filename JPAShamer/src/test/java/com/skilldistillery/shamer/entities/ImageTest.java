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

class ImageTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Image image;

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
		image = em.find(Image.class, 1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		image = null;
	}
	
	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_Image_has_id() {
		assertNotNull(image);
		assertEquals(1, image.getId());
	}

	@Test
	void test_Image_Url_has_Content() {
		assertNotNull(image.getImageUrl());
	}
	
	@Test
	void test_Image_has_complaint_id() {
		assertNotNull(image.getComplaint().getId());
		assertEquals(1, image.getComplaint().getId());
	}
}
