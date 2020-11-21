package com.udec.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chef")
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_chef;
	
	@Column(name = "nombre", nullable = false, length = 30)
	@Size(min = 3, max = 30, message = "Nombre debe tener entre 3 y 30 carácteres")
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 30)
	@Size(min = 3, max = 30, message = "Apellido debe tener entre 3 y 30 carácteres")
	private String apellido;
	
	@Column(name = "sueldo", nullable = false)
	@Min(value = 1000000, message = "Digite un sueldo entre $1.000.000 y $6.000.000")
	@Max(value = 6000000, message = "Digite un sueldo entre $1.000.000 y $6.000.000")
	private Long sueldo;
	
	@OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Comida> comida;
	
	@NotNull(message = "Dirección es obligatoria")
	@OneToOne(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;
	
	public Integer getId_chef() {
		return id_chef;
	}

	public void setId_chef(Integer id_chef) {
		this.id_chef = id_chef;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getSueldo() {
		return sueldo;
	}

	public void setSueldo(Long sueldo) {
		this.sueldo = sueldo;
	}

	public List<Comida> getComida() {
		return comida;
	}

	public void setComida(List<Comida> comida) {
		this.comida = comida;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

}
