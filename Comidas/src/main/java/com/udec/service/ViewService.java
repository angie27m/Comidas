package com.udec.service;

import org.springframework.data.domain.Page;

import com.udec.view.ChefView;

public interface ViewService {
	
	public Page<ChefView> listarPaginado(boolean lazy, int page, int size);
	
	public ChefView getById(int id);
	
}
