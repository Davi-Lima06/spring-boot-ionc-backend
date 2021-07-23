package com.davi.cursomc.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CategoriaTest {

	Categoria categoria;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		categoria = new Categoria();
		
		System.out.println("Iniciando");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSet() {
		
		categoria.setNome("Davi");
		assertEquals("Davi", categoria.getNome());
	}

}
