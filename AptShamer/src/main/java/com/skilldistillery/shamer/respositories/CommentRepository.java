package com.skilldistillery.shamer.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.shamer.entities.Comment;

public interface CommentRepository extends JpaRepository <Comment, Integer>{

	List<Comment> findByComplaint_Id(int id);
	

}
