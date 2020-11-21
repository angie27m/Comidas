package com.udec.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.exception.ModelNotFoundException;
import com.udec.repository.ViewRepo;
import com.udec.service.ViewService;
import com.udec.view.ChefView;

@Service
public class ViewServiceImp implements ViewService{
	
	@Autowired
	private ViewRepo repo;

	@Override
	public Page<ChefView> listarPaginado(boolean lazy, int page, int size) {
		Page<ChefView> listaPaginaView = repo.findAll(PageRequest.of(page, size, Sort.by("name").ascending()));;
		
		if(lazy) {
			for (ChefView view : listaPaginaView.getContent()) {
				
			}			
		}
		
		return listaPaginaView;
	}

	@Override
	public ChefView getById(int id) {
		ChefView view = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Esta informacion no existe"));
		return view;
	}

}
