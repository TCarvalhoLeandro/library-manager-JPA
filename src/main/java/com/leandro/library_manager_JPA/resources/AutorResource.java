package com.leandro.library_manager_JPA.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.library_manager_JPA.entities.Autor;
import com.leandro.library_manager_JPA.services.AutorService;

@RestController
@RequestMapping(value="/autores")
public class AutorResource {
	
	@Autowired
	private AutorService service;

	// Metodo que vai na camada service busca todos os autores e retorna pra quem chamou
	@GetMapping
	public ResponseEntity<List<Autor>> findAll(){
		List<Autor> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	// Metodo que vai na camada service busca autor por id retorna pra quem chamou
	@GetMapping(value = "/{id}")
	public ResponseEntity<Autor> findById(@PathVariable Long id){
		Autor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
