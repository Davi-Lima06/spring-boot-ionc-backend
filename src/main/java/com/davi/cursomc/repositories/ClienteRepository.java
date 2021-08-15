package com.davi.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davi.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer >{ // CLASSE DE ACESSO A DADOS
																				 // ESSA CLASSE QUE VAI ALTERAR OS DADOS DO BANCO DE DADOS 

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
 