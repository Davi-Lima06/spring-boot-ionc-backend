package com.davi.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davi.cursomc.domain.Cidade;
import com.davi.cursomc.domain.Cliente;
import com.davi.cursomc.domain.Endereco;
import com.davi.cursomc.domain.enums.TipoCliente;
import com.davi.cursomc.dto.ClienteDTO;
import com.davi.cursomc.dto.ClienteNewDto;
import com.davi.cursomc.repositories.ClienteRepository;
import com.davi.cursomc.repositories.EnderecoRepository;
import com.davi.cursomc.services.exceptions.DataIntegrityException;
import com.davi.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {  // ESSA CLASSE SERVE PARA OPERAÇÕES E CONSULTAS
	
	@Autowired // ESSA DEPENDENCIA VAI SER INSTANCIADA AUTOMATICAMENTE PELO SPRING
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	public Cliente find(Integer id) throws ObjectNotFoundException {
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.saveAll(obj.getEnderecos());
		return obj;
	} 
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possível excluir pq há entidades relacionadas");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page,Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDto objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(),objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(),objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTefefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!= null) {
			cli.getTefefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!= null) {
			cli.getTefefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
	


}
