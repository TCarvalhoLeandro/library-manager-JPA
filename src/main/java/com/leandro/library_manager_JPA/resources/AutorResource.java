package com.leandro.library_manager_JPA.resources;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.library_manager_JPA.entities.Autor;

@RestController
@RequestMapping(value="/autores")
public class AutorResource {

	@GetMapping
	public ResponseEntity<Autor> findAll(){
		Autor a = new Autor("Machado de Assis", "Brasileiro", LocalDate.of(1839, 6, 21));
		return ResponseEntity.ok().body(a);
	}
}
