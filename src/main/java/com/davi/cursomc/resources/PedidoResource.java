package com.davi.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.davi.cursomc.domain.Pedido;
import com.davi.cursomc.services.BoletoService;
import com.davi.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;

	@RequestMapping(value = "{id}",method = RequestMethod.GET )
	public ResponseEntity<Pedido> find(@PathVariable Integer id) { // O RESPONSEENTITY É UM TIPO ESPECIAL DO SPRING QUE ELE JÁ ARMAZENA VARIAS INFORMAÇÕES
															  // DE UMA RESPOSTA HTTP PARA UM SERVIÇO REST
		
		Pedido obj = service.find(id);
		
		return ResponseEntity.ok().body(obj) ;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
				
		return ResponseEntity.created(uri).build();
				
	}
}
