package com.davi.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.davi.cursomc.domain.Categoria;
import com.davi.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "{id}",method = RequestMethod.GET )
	public ResponseEntity<?> find(@PathVariable Integer id) { // O RESPONSEENTITY É UM TIPO ESPECIAL DO SPRING QUE ELE JÁ ARMAZENA VARIAS INFORMAÇÕES
															  // DE UMA RESPOSTA HTTP PARA UM SERVIÇO REST
		
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj) ;
	}
}
