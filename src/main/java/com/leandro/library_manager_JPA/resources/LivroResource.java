package com.leandro.library_manager_JPA.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.services.LivroService;

@RestController
@RequestMapping(value="/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	// Metodo que chama service para retornar todos os livros do repository (banco) para uma requisição web
	@GetMapping
	public ResponseEntity<List<Livro>> findAll(){
		List<Livro> livro = service.findAll();
		return ResponseEntity.ok().body(livro);
	}
	
	// Metodo que chama service para retornar um livro por id do repository (banco) para uma requisição web
	@GetMapping(value="/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		Livro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
