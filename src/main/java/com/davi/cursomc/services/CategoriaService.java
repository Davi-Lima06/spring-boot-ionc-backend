package com.davi.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davi.cursomc.domain.Categoria;
import com.davi.cursomc.repositories.CategoriaRepository;
import com.davi.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {  // ESSA CLASSE SERVE PARA OPERAÇÕES E CONSULTAS
	
	@Autowired // ESSA DEPENDENCIA VAI SER INSTANCIADA AUTOMATICAMENTE PELO SPRING
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}

}
