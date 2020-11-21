package com.udec.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.service.ViewService;
import com.udec.view.ChefView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Vistas")
@RestController
@RequestMapping("/vistas")
public class ViewController {
	
	@Autowired
	ViewService service;
	
	@GetMapping(path = "/consultar/{lazy}/{page}/{size}")
	@ApiOperation(value = "Servicio consultar", notes = "Este servicio se encarga de consultar los chefs existentes")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public  ResponseEntity<Page<ChefView>> rentorPageable(@PathVariable boolean lazy, @PathVariable int page, @PathVariable int size) {		
		Page<ChefView> listarVista = service.listarPaginado(lazy, page, size);
		return new ResponseEntity<Page<ChefView>>(listarVista, HttpStatus.OK);
	}
	
	@GetMapping(path = "/consultarPorId/{id}")
	@ApiOperation(value = "Servicio consultar por ID", notes = "Este servicio se encarga de consultar el objeto por ID")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Esta informacion no existe") })
	public ResponseEntity<ChefView> consultarPorId(@PathVariable @NotEmpty @NotNull int id) throws IOException {
		ChefView view = service.getById(id);
		return new ResponseEntity<ChefView>(view, HttpStatus.OK);
	}
	
}
