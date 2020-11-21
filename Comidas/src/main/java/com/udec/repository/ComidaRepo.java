package com.udec.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Comida;

@Repository
public interface ComidaRepo extends JpaRepository<Comida, Integer>{
	
	@Query("SELECT co FROM Comida co WHERE lower(co.nombre) = lower(:nombre) and lower(co.tipo) = lower(:tipo)")
	public Comida consultarExistente(
			@Param("nombre") String nombre,
			@Param("tipo") String tipo);
}
