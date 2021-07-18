package com.davi.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davi.cursomc.domain.Cliente;
import com.davi.cursomc.repositories.ClienteRepository;
import com.davi.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {  // ESSA CLASSE SERVE PARA OPERAÇÕES E CONSULTAS
	
	@Autowired // ESSA DEPENDENCIA VAI SER INSTANCIADA AUTOMATICAMENTE PELO SPRING
	private ClienteRepository repo;
	
	public Cliente find(Integer id) throws ObjectNotFoundException {
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}

}
