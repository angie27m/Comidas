package com.udec.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Chef;

@Repository
public interface ChefRepo extends JpaRepository<Chef, Integer> {
	
	@Query("SELECT cf FROM Comida co JOIN co.chef cf WHERE lower(co.nombre) = lower(:nombre) and lower(co.tipo) = lower(:tipo)")
	public Chef consultarChefPorComida(
			@Param("nombre") String nombre,
			@Param("tipo") String tipo);
	
	public List<Chef> findByComida_nombre(String nombre);
	
	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE Direccion set descripcion = :descripcion, barrio = :barrio where chef_id_chef = :id", nativeQuery = true)
	 void update2(@Param("descripcion") String descripcion,
			 @Param("barrio") String barrio,
			 @Param("id") Integer id);
}
