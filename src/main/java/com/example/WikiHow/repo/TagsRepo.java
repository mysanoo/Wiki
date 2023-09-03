package com.example.WikiHow.repo;

import com.example.WikiHow.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepo extends JpaRepository<Tag, Integer> {
}
