package com.nagesh.apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagesh.apis.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
