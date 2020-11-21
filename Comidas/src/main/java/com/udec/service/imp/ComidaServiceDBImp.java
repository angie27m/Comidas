package com.udec.service.imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udec.dto.ComidaDto;
import com.udec.entity.Chef;
import com.udec.entity.Comida;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.ChefRepo;
import com.udec.repository.ComidaRepo;
import com.udec.service.ComidaDBService;

@Service
public class ComidaServiceDBImp implements ComidaDBService {

	@Autowired
	private ComidaRepo comiRepo;

	@Autowired
	private ChefRepo chefRepo;

	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public String save(Comida comida) {
		if (comida.getChef() == null || comida.getChef().getId_chef() == null) {
			throw new ArgumentRequiredException("Id chef es requerido");
		} else {
			boolean existencia = chefRepo.existsById(comida.getChef().getId_chef());
			if (existencia) {
				if (comiRepo.consultarExistente(comida.getNombre(), comida.getTipo()) == null) {
			        comiRepo.save(comida);
					return "Se ha guardado la comida";
				} else {
					throw new IllegalArgumentException("Esta comida ya existe");
				}
			} else {
				throw new ArgumentRequiredException("Este chef no existe");
			}
		}
	}

	@Override
	public List<Comida> get() {
		return comiRepo.findAll();
	}

	@Override
	public String update(Comida comida) {
		if (comida.getId_comida() == null) {
			throw new ArgumentRequiredException("Id comida es requerido");
		}
		Comida comi = this.getById(comida.getId_comida());
		if(comida.getNombre() == null) {
			comida.setNombre(comi.getNombre());
		}else if(comida.getPrecio() == null) {
			comida.setPrecio(comi.getPrecio());
		}else if(comida.getTamanio() == null) {
			comida.setTamanio(comi.getTamanio());
		}else if(comida.getTipo() == null){
			comida.setTipo(comi.getTipo());
		}
		Comida com = modelMapper.map(comida, Comida.class);
		comiRepo.save(com);
		return "Se ha editado exitosamente";
	}

	@Override
	public Comida getById(Integer id) {
		Comida comi = comiRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("Esta comida no existe"));
		return comi;
	}

	@Override
	public void delete(Integer id) {
		boolean existencia = chefRepo.existsById(id);
		System.out.println(existencia);
		if (existencia) {
			comiRepo.deleteById(id);
		} else {
			throw new ModelNotFoundException("Esta comida no existe");
		}
	}
}
