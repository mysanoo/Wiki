package com.example.WikiHow.repo;

import com.example.WikiHow.entity.PostPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostPartRepo extends JpaRepository<PostPart, Integer> {


}
