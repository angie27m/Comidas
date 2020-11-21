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

import com.udec.entity.Cliente;
import com.udec.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@PreAuthorize("hasAuthority('Admin')")
@Api(tags = "Clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping(path = "/consultar")
	@ApiOperation(value = "Servicio consultar", notes = "Este servicio se encarga de consultar los clientes existentes")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<List<Cliente>> consultar() throws IOException {
		List<Cliente> listaClientes = service.get();
		return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
	}
	
	@GetMapping(path = "/consultarOrdenado")
	@ApiOperation(value = "Servicio consultar", notes = "Este servicio se encarga de consultar por apellidos de los clientes existentes")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<List<Cliente>> consultarApellidos() throws IOException {
		List<Cliente> listaClientes = service.getByApellido();
		return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
	}
	
	@GetMapping(path = "/consultarPorId/{id}")
	@ApiOperation(value = "Servicio consultar por ID", notes = "Este servicio se encarga de consultar el objeto por ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Este cliente no existe") })
	public ResponseEntity<Cliente> consultarPorId(@PathVariable @NotEmpty @NotNull int id) throws IOException {
		Cliente cliente = service.getById(id);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping(path = "/guardar")
	@ApiOperation(value = "Servicio guardar", notes = "Este servicio se encarga de guardar un nuevo objeto")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Se ha guardado con exito"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_ACCEPTABLE, message = "Este cliente ya existe") })
	public ResponseEntity<String> guardar(@Valid @RequestBody Cliente cliente) throws Exception {
		String respuesta = service.save(cliente);
		return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);	
	}

	@PutMapping(path = "/editar")
	@ApiOperation(value = "Servicio editar", notes = "Este servicio se encarga de editar el objeto según su ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha editado exitosamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Este cliente no existe") })
	public ResponseEntity<String> editar(@Valid @RequestBody Cliente cliente) throws Exception {
		String respuesta = service.update(cliente);
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	@DeleteMapping(path = "/eliminar/{id}")
	@ApiOperation(value = "Servicio eliminar por ID", notes = "Este servicio se encarga de eliminar el objeto según su ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Se ha eliminado exitosamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Este cliente no existe") })
	public ResponseEntity<String> eliminar(@PathVariable @NotEmpty @NotNull int id) throws Exception {
		service.delete(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}

