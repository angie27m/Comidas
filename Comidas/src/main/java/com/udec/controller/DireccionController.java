package com.udec.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.entity.Direccion;
import com.udec.service.DireccionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Direcciones")
@RestController
@RequestMapping("/direcciones")
public class DireccionController {

	@Autowired
	private DireccionService service;
	
	@PutMapping(path = "/editar")
	@ApiOperation(value = "Servicio editar", notes = "Este servicio se encarga de editar el objeto según su ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha editado exitosamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Esta direccion no existe") })
	public ResponseEntity<String> editar(@Valid @RequestBody Direccion direccion) throws Exception {
		String respuesta = service.update(direccion);
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@PutMapping(path = "/editar2")
	@ApiOperation(value = "Servicio editar", notes = "Este servicio se encarga de editar el objeto según su ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha editado exitosamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Esta direccion no existe") })
	public ResponseEntity<String> editar2(@Valid @RequestBody Direccion direccion) throws Exception {
		String respuesta = service.update(direccion);
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
}
