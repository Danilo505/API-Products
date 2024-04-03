package com.example.repository;

import com.example.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SalesRepository extends JpaRepository <Sales, Integer> {
}
