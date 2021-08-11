package com.davi.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.davi.cursomc.domain.Categoria;
import com.davi.cursomc.domain.Produto;
import com.davi.cursomc.repositories.CategoriaRepository;
import com.davi.cursomc.repositories.ProdutoRepository;
import com.davi.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ProdutoService {  // ESSA CLASSE SERVE PARA OPERAÇÕES E CONSULTAS
	
	@Autowired // ESSA DEPENDENCIA VAI SER INSTANCIADA AUTOMATICAMENTE PELO SPRING
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
	public Produto find(Integer id) throws ObjectNotFoundException {
		
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page,Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = catRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
