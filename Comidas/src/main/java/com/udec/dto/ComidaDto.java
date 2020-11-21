package com.udec.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.udec.entity.Chef;

public class ComidaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id_comida;
	
	@Size(min = 4, max = 30, message = "Nombre debe tener entre 4 y 30 carácteres")
	private String nombre;

	@Size(min = 3, max = 30, message = "Tipo debe tener entre 3 y 30 carácteres")
	private String tipo;

	@Min(value = 1000, message = "El precio debe ser mayor a $1000")
	private Long precio;

	@Size(min = 3, max = 30, message = "Tamaño debe tener entre 3 y 30 carácteres")
	private String tamanio;
	
	private Chef chef;

	public ComidaDto() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getId_comida() {
		return id_comida;
	}

	public void setId_comida(Integer id_comida) {
		this.id_comida = id_comida;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
}
