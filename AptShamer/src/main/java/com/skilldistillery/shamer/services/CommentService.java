package com.skilldistillery.shamer.services;

import java.util.List;

import com.skilldistillery.shamer.entities.Comment;

public interface CommentService {
	
	public List<Comment> index(int id);
	
	public Comment show(int id, int cid);
	
	public Comment create(int id, Comment comment);
	
	public Comment update(int id, int cid, Comment comment);
	
	public Boolean destroy(int id, int cid);
	
}
