package com.rafael.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.crud_spring.model.Curso;

@Repository
public interface CusroRepository extends JpaRepository<Curso, Long> {
    
}