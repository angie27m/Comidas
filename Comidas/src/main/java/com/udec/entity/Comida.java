package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "comida")
public class Comida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_comida;

	@Column(name = "nombre", nullable = false, length = 30)
	@Size(min = 4, max = 30, message = "Nombre debe tener entre 4 y 30 carácteres")
	private String nombre;

	@Column(name = "tipo", nullable = false, length = 30)
	@Size(min = 3, max = 30, message = "Tipo debe tener entre 3 y 30 carácteres")
	private String tipo;

	@Column(name = "precio", nullable = false)
	@Min(value = 1000, message = "El precio debe ser mayor a $1000")
	private Long precio;

	@Column(name = "tamanio", nullable = false, length = 30)
	@Size(min = 3, max = 30, message = "Tamaño debe tener entre 3 y 30 carácteres")
	private String tamanio;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "id_chef", nullable = false, foreignKey = @ForeignKey(name = "FK_Chef_Comida"))	
	private Chef chef;
	
	public Integer getId_comida() {
		return id_comida;
	}

	public void setId_comida(Integer id_comida) {
		this.id_comida = id_comida;
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

	@JsonIgnore
	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
}
