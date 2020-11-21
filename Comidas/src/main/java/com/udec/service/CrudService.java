package com.udec.service;

import java.util.List;

public interface CrudService <T, ID>{

	public List<T> get();
	
	public T getById(ID id);
	
	public String save(T entity);
	
	public String update(T entity);
	
	public void delete(ID id);
}
