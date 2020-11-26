package com.udec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.dto.ComidaDto;
import com.udec.entity.Comida;
import com.udec.service.ComidaDBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@PreAuthorize("hasAuthority('Invitado') OR hasAuthority('Admin')")
@Api(tags = "Comidas")
@RestController
@RequestMapping("/comidas")
public class ComidaController {

	@Autowired
	ComidaDBService comidaServicedb;

	@GetMapping(path = "/consultar")
	@ApiOperation(value = "Servicio consultar", notes = "Este servicio se encarga de consultar las comidas existentes")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<List<Comida>> consultar() throws IOException {
		List<Comida> listaComidas = comidaServicedb.get();
		return new ResponseEntity<List<Comida>>(listaComidas, HttpStatus.OK);
	}
	
	@GetMapping(path = "/consultarPorId/{id}")
	@ApiOperation(value = "Servicio consultar por ID", notes = "Este servicio se encarga de consultar el objeto por ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Esta comida no existe") })
	public ResponseEntity<Comida> consultarPorId(@PathVariable @NotEmpty @NotNull int id) throws IOException {
		Comida comida = comidaServicedb.getById(id);
		return new ResponseEntity<Comida>(comida, HttpStatus.OK);
	}

	@PostMapping(path = "/guardar")
	@ApiOperation(value = "Servicio guardar", notes = "Este servicio se encarga de guardar un nuevo objeto")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Se ha guardado con exito"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_ACCEPTABLE, message = "Esta comida ya existe") })
	public ResponseEntity<String> guardar(@Valid @RequestBody Comida comida) throws Exception {
		String respuesta = comidaServicedb.save(comida);
		return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);	
	}

	@PutMapping(path = "/editar")
	@ApiOperation(value = "Servicio editar", notes = "Este servicio se encarga de editar el objeto según su ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha editado exitosamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Esta comida no existe") })
	public ResponseEntity<String> editar(@Valid @RequestBody Comida comida) throws Exception {
		String respuesta = comidaServicedb.update(comida);
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	@DeleteMapping(path = "/eliminar/{id}")
	@ApiOperation(value = "Servicio eliminar por ID", notes = "Este servicio se encarga de eliminar el objeto según su ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Se ha eliminado exitosamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Esta comida no existe") })
	public ResponseEntity<String> eliminar(@PathVariable @NotEmpty @NotNull int id) throws Exception {
		comidaServicedb.delete(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
}
