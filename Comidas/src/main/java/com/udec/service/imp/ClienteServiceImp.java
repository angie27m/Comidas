package com.udec.service.imp;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.entity.Cliente;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.ClienteRepo;
import com.udec.service.ClienteService;

@Service
public class ClienteServiceImp implements ClienteService{

	@Autowired
	private ClienteRepo repo;
	
	@Override
	public String save(Cliente cliente) {
		if(repo.findByCedula(cliente.getCedula())==null) {
			repo.save(cliente);
			return "Se ha guardado el cliente";
		}else {
			throw new IllegalArgumentException("Este cliente ya existe");
		}
		
		
	}

	@Override
	public List<Cliente> get(){
		return repo.findAll(Sort.by("nombre").ascending());
	}

	@Override
	public String update(Cliente cliente) {
		if(cliente.getId_cliente() == null) {
			throw new ArgumentRequiredException("Id cliente es requerido");
		}
		this.getById(cliente.getId_cliente());
		if(repo.findByCedula(cliente.getCedula())!=null) {
			Cliente cli = repo.findByCedula(cliente.getCedula());
			if(cli.getId_cliente()==cliente.getId_cliente()) {
				repo.save(cliente);
				return "Se ha actualizado el cliente";
			}else {
				throw new IllegalArgumentException("Este cliente no se puede editar");
			}	
		}else {
			repo.save(cliente);
			return "Se ha actualizado el cliente";
		}
	}

	@Override
	public Cliente getById(Integer id) {
		Cliente cliente = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Este cliente no existe"));
		return cliente;
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<Cliente> getByApellido() {
		List<Cliente> cliente = repo.findByOrderByApellidoAsc();
		if(cliente.isEmpty()) {
			throw new IllegalArgumentException("No hay clientes");
		}else {
			return cliente;
		}
	}

}
