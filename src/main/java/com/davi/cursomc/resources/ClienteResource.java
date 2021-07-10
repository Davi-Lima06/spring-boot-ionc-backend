package com.davi.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.davi.cursomc.domain.Cliente;
import com.davi.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@RequestMapping(value = "{id}",method = RequestMethod.GET )
	public ResponseEntity<?> find(@PathVariable Integer id) { // O RESPONSEENTITY É UM TIPO ESPECIAL DO SPRING QUE ELE JÁ ARMAZENA VARIAS INFORMAÇÕES
															  // DE UMA RESPOSTA HTTP PARA UM SERVIÇO REST
		
		Cliente obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj) ;
	}
}
