package com.udec.service;

import java.util.List;

import com.udec.dto.ComidaDto;
import com.udec.entity.Chef;
import com.udec.entity.Direccion;

public interface ChefService extends CrudService<Chef, Integer>{
	
	public abstract void deletePro(int id);
	
	public abstract Chef getByComida(ComidaDto comida);
	
	public abstract List<Chef> getByComida2(String nombre);
	
	public abstract String update2(Direccion entity);
}
