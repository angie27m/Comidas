package com.udec.entity;

import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;


@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cliente;
	
	@Column(name = "cedula", nullable = false)
	@Min(value = 10000, message = "Digite una cédula válida")
	@Max(value = 999999999, message = "Digite una cédula válida")
	private Long cedula;
	
	@Column(name = "nombre", nullable = false, length = 30)
	@Size(min = 4, max = 30, message = "Nombre debe tener entre 4 y 30 carácteres")
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 30)
	@Size(min = 4, max = 30, message = "Apellido debe tener entre 4 y 30 carácteres")
	private String apellido;
	
	@Column(name = "correo", nullable = false, length = 100)
	@Email(message = "Correo no válido :(, sigue el ejemplo: correo@udec.com", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String correo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Column(name = "fecha_nacimiento", nullable = false)
	@Past(message = "La fecha debe ser menor a la actual")
    private LocalDate fecha_nacimiento;

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

}
