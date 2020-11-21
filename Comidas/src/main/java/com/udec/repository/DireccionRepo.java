package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udec.entity.Direccion;

@Repository
public interface DireccionRepo extends JpaRepository<Direccion, Integer> {
	
}
