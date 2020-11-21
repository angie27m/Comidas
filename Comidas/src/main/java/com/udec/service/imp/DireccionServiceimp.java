package com.udec.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udec.entity.Direccion;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.DireccionRepo;
import com.udec.service.DireccionService;

@Service
public class DireccionServiceimp implements DireccionService{

	@Autowired
	private DireccionRepo repo;
	
	@Override
	public List<Direccion> get() {
		return repo.findAll();
	}

	@Override
	public Direccion getById(Integer id) {
		Direccion direccion = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Esta direccion no existe"));
		return direccion;
	}

	@Override
	public String save(Direccion entity) {
		repo.save(entity);
		return "Se ha guardado la direccion";
	}

	@Override
	public String update(Direccion entity) {
		if(entity.getId()== null) {
			throw new ArgumentRequiredException("Id direccion es requerido");
		}
		boolean existencia = repo.existsById(entity.getId());
		if(existencia) {
			repo.save(entity);
			return "Se ha editado la direccion";
		}else {
			throw new ModelNotFoundException("Esta direccion no existe");
		}
	}

	@Override
	public void delete(Integer id) {
		boolean existencia = repo.existsById(id);
		System.out.println(existencia);
		if (existencia) {
			repo.deleteById(id);
		} else {
			throw new ModelNotFoundException("Esta direccion no existe");
		}
	}

	@Override
	public String update2(Direccion entity) {
		return null;
	}

}
