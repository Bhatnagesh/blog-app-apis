package com.nagesh.apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagesh.apis.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
