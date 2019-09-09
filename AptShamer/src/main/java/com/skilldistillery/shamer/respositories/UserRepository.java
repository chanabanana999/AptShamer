package com.skilldistillery.shamer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.shamer.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("Select u from User u WHERE u.username = :username")
	User queryByUsername(@Param("username") String username);
	
	User findByUsername(String name);
	
}