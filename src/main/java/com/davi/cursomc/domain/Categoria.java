package com.davi.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity //SERVE PARA MAPEAMENTO NO BANCO DE DADOS
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // SERVE PARA DEFINIR A ESTRATÉGIA DE GERAÇÃO AUTOMÁTICA DOS ID DAS CATEGORIAS
	private Integer id;
	private String nome;
	
	 // FAZER ISSO DO LADO QUE VC QUER QUE VENHA OS OBJETOS ASSOCIADOS
	@ManyToMany(mappedBy = "categorias") // ESSA ANOTAÇÃO SERVE PARA ASSOCIAR CLASSES NO BANCO DE DADOS, NO CASO MUITOS PARA MUITOS
	private List<Produto> produtos = new ArrayList<>(); // SERVE PARA ASSOCIAR ESSA CLASSE A OUTRA CLASSE, NO CASO A CLASSE PRODUTO ESTA AGORA ASSOCIADA A CLASSE CATEGORIA 
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Categoria () {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}

	
	
	

}
