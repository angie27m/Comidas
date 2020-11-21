package com.udec.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udec.dto.ComidaDto;
import com.udec.entity.Chef;
import com.udec.entity.Comida;
import com.udec.entity.Direccion;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.ChefRepo;
import com.udec.repository.DireccionRepo;
import com.udec.service.ChefService;

@Service
public class ChefServiceImp implements ChefService{

	@Autowired
	private ChefRepo repo;
	
	@Override
	public String save(Chef chef) {
		if(chef.getComida() != null) {
			for(Comida comida: chef.getComida()) {
				comida.setChef(chef);
			}		
		}
		if(chef.getDireccion() != null) {
			chef.getDireccion().setChef(chef);
		}
		repo.save(chef);
		return "Se ha guardado exitosamente";
	}

	@Override
	public List<Chef> get() {
		return repo.findAll();
	}

	@Override
	public String update(Chef chef) {
		if(chef.getId_chef() == null) {
			throw new ArgumentRequiredException("Id chef es requerido");
		}
		
		Chef ch = this.getById(chef.getId_chef());
		ch.setNombre(chef.getNombre());
		ch.setApellido(chef.getApellido());
		ch.setSueldo(chef.getSueldo());
		ch.getDireccion().setDescripcion(chef.getDireccion().getDescripcion());
		ch.getDireccion().setBarrio(chef.getDireccion().getBarrio());
		repo.save(ch);
		return "Se ha actualizado el chef";
	}

	@Override
	public Chef getById(Integer id) {
		Chef chef = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Este chef no existe"));
		return chef;
	}
	
	@Override
	public Chef getByComida(ComidaDto comida) {
		if(repo.consultarChefPorComida(comida.getNombre(), comida.getTipo())==null) {
			throw new ModelNotFoundException("No hay chef para esta comida");
		}else {
			Chef chef = repo.consultarChefPorComida(comida.getNombre(), comida.getTipo());
			return chef;
		}
	}
	
	@Override
	public List<Chef> getByComida2(String nombre) {
		if(repo.findByComida_nombre(nombre)==null) {
			throw new ModelNotFoundException("No hay chef para esta comida");
		}else {
			List<Chef> chef = repo.findByComida_nombre(nombre);
			return chef;
		}
	}

	@Override
	public void delete(Integer id) {
		Chef chef = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Chef no encontrado"));	
		
		//throw new BusinessLogicException("Se debe eliminar primero todos los libros asociados al Autor");
		//repo.deleteById(idAutor);
		repo.delete(chef);
	}
	
	@Override
	public void deletePro(int id) {
		Chef chef = this.getById(id);
		if(chef.getComida().isEmpty()) {
			repo.delete(chef);
		}else {
			throw new ModelNotFoundException("Se debe eliminar primero todos las comidas asociadas al Chef");
		}
	}

	@Override
	public String update2(Direccion entity) {
		if(entity.getId()== null) {
			throw new ArgumentRequiredException("Id direccion es requerido");
		}
		boolean existencia = repo.existsById(entity.getId());
		if(existencia) {
			repo.update2(entity.getDescripcion(), entity.getBarrio(), entity.getId());
			return "Se ha editado la direccion";
		}else {
			throw new ModelNotFoundException("Esta direccion no existe");
		}
	}
}
