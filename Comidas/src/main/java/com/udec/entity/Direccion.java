package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "direccion")
public class Direccion {
	
	@Id
	private Integer id;
	
	@NotNull
	@Size(min = 3,max = 50, message = "La descripción debe ser entre 3 y 50 carácteres")	
	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;

	@NotNull
	@Size(min = 3,max = 20, message = "EL barrio debe ser entre 3 y 20 carácteres")	
	@Column(name = "barrio", nullable = false, length = 20)	
	private String barrio;
	
	@OneToOne
	@MapsId
	private Chef chef;

	public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getBarrio() {
		return barrio;
	}

	@JsonIgnore
	public Chef getChef() {
		return chef;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}		
}
