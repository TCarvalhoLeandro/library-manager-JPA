package com.leandro.library_manager_JPA.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.library_manager_JPA.entities.Leitor;
import com.leandro.library_manager_JPA.services.LeitorService;

@RestController
@RequestMapping(value = "/leitores")
public class LeitorResource {

	@Autowired
	private LeitorService service;

	@GetMapping
	public ResponseEntity<List<Leitor>> findAll() {
		List<Leitor> leitores = service.findAll();
		return ResponseEntity.ok().body(leitores);
	}

	// Metodo que chama service para retornar um Leitor por id do repository (banco)
	// para uma requisição web
	@GetMapping(value = "/{id}")
	public ResponseEntity<Leitor> findById(@PathVariable Long id) {
		Leitor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
