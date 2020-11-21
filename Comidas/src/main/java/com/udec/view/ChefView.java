package com.udec.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT ch.id_chef as id,\n" +
		"		concat(ch.nombre, ' ', ch.apellido) as name,\n" +
        "       ch.sueldo ,\n" +
        "       d.descripcion,\n" +
        "       d.barrio,\n" +
        "		(SELECT COUNT(c) FROM Comida c where c.id_chef = ch.id_chef) as cant_comidas"+
        " 		FROM Chef ch \n" +
        "       LEFT JOIN Direccion d on d.chef_id_chef = ch.id_chef\n" +
        "       LEFT JOIN Comida c on c.id_chef = ch.id_chef")
public class ChefView {
	
	@Id
	private Integer id;
	
	@Column
	private String name;
	
	@Column 
	private Long sueldo;
	
	@Column 
	private String descripcion;
	
	@Column
	private String barrio;
	
	@Column
	private Integer cant_comidas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSueldo() {
		return sueldo;
	}

	public void setSueldo(Long sueldo) {
		this.sueldo = sueldo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Integer getCant_comidas() {
		return cant_comidas;
	}

	public void setCant_comidas(Integer cant_comidas) {
		this.cant_comidas = cant_comidas;
	}
	
}
