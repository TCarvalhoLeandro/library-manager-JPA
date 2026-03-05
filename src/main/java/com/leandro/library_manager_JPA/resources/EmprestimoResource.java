package com.leandro.library_manager_JPA.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.services.EmprestimoService;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource {

	@Autowired
	private EmprestimoService service;

	@GetMapping
	public ResponseEntity<List<Emprestimo>> findAll() {
		List<Emprestimo> emprestimo = service.findAll();
		return ResponseEntity.ok().body(emprestimo);
	}

	// Metodo que chama service para retornar um Emprestimo por id do repository
	// (banco) para uma requisição web
	@GetMapping(value = "/{id}")
	public ResponseEntity<Emprestimo> findById(@PathVariable Long id) {
		Emprestimo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
