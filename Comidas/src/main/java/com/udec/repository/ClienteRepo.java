package com.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udec.entity.Cliente;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
	
	public Cliente findByCedula(Long Cedula);
	
	public List<Cliente> findByOrderByApellidoAsc();
}
