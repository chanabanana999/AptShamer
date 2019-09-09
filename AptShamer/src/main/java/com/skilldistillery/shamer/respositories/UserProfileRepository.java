package com.skilldistillery.shamer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.shamer.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

}
