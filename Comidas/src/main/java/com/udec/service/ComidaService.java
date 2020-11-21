package com.udec.service;

import java.io.IOException;
import java.util.List;

import com.udec.dto.ComidaDto;


public interface ComidaService {

	public abstract String save(ComidaDto comida) throws Exception;

	public abstract String update(ComidaDto comida) throws Exception;

	public abstract void delete(int id) throws Exception;

	public abstract List<ComidaDto> get() throws IOException;

	public abstract ComidaDto getById(int id) throws IOException;
}
