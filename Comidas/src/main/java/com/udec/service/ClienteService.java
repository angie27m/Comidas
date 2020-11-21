package com.udec.service;

import java.io.IOException;
import java.util.List;

import com.udec.entity.Cliente;
import com.udec.entity.Direccion;

public interface ClienteService extends CrudService<Cliente, Integer>{
		
	public abstract List<Cliente> getByApellido();
}

