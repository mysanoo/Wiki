package com.example.WikiHow.repo;

import com.example.WikiHow.entity.Approved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovedRepo extends JpaRepository<Approved, Integer> {



}
