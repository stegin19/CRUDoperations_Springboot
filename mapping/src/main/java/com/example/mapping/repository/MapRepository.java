package com.example.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mapping.model.Map;

public interface MapRepository extends JpaRepository<Map, Long>{
    
}
