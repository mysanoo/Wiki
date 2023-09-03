package com.example.WikiHow.repo;

import com.example.WikiHow.entity.Tips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipsRepo extends JpaRepository<Tips, Integer> {
}
