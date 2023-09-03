package com.example.WikiHow.repo;

import com.example.WikiHow.entity.Warnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarningsRepo extends JpaRepository<Warnings,Integer> {
}
