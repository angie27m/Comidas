/*package com.udec.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udec.dto.ComidaDto;
import com.udec.service.ComidaService;

@Service
public class ComidaServiceImp implements ComidaService {

	List<ComidaDto> listaComidas = new ArrayList<>();
	File documento = new File("Comida.txt");

	@Override
	public String save(ComidaDto comida) throws Exception {
		//if (!validarExistencia(comida.getId())) {
			listaComidas.add(comida);
			guardarArchivo();
			return "Se ha guardado con exito";
		/*} else {
			throw new IllegalArgumentException("Esta comida ya existe");
		}
	}

	@Override
	public String update(ComidaDto comida) throws Exception {
		/*if (validarExistencia(comida.getId())) {
			ComidaDto comi = new ComidaDto();
			comi = getById(comida.getId());
			listaComidas.remove(comi);
			listaComidas.add(comida);
			guardarArchivo();
			return "Se ha editado exitosamente";
		} else {
			throw new NullPointerException("Esta comida no existe");
		}
		return
	}

	@Override
	public String delete(int id) throws Exception {
		ComidaDto comi = new ComidaDto();
		comi = getById(id);
		listaComidas.remove(comi);
		guardarArchivo();
		return "Se ha eliminado exitosamente";
	}

	@Override
	public List<ComidaDto> get() throws IOException {
		listaComidas = new ArrayList<ComidaDto>();
		lecturaArchivo();
		return listaComidas;
	}

	@Override
	public ComidaDto getById(int id) throws IOException {
		listaComidas = new ArrayList<>();
		lecturaArchivo();
		for (ComidaDto comida : listaComidas) {
			if (comida.getId() == id) {
				return comida;
			} 			
		}
		throw new NullPointerException("Esta comida no existe");
	}

	public boolean validarExistencia(int id) throws IOException {
		listaComidas = new ArrayList<>();
		lecturaArchivo();
		for (ComidaDto comida : listaComidas) {
			if (comida.getId() == id) {
				return true;
			}
		}
		return false;
	}

	private void guardarArchivo() throws Exception {
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(documento));
			salida.writeObject(listaComidas);
		} catch (Exception e) {
			e.getStackTrace();
			throw new Exception("El archivo no pudo ser guardado");
		}
	}

	private void lecturaArchivo() throws IOException {
		FileInputStream archivo;
		try {
			archivo = new FileInputStream(documento);
			ObjectInputStream leer = new ObjectInputStream(archivo);
			listaComidas = (ArrayList<ComidaDto>) leer.readObject();
			leer.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException("Error en la lectura del archivo");

		}
	}
}*/
