package com.davi.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davi.cursomc.domain.Pedido;
import com.davi.cursomc.repositories.PedidoRepository;
import com.davi.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class PedidoService {  // ESSA CLASSE SERVE PARA OPERAÇÕES E CONSULTAS
	
	@Autowired // ESSA DEPENDENCIA VAI SER INSTANCIADA AUTOMATICAMENTE PELO SPRING
	private PedidoRepository repo;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}

}
