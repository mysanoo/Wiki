package com.example.WikiHow.repo;

import com.example.WikiHow.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends JpaRepository<Category, Integer> {

}
