package com.leandro.library_manager_JPA.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	// Metodo que vai na camada service e chama o metodo insert para inserir um Autor
	@PostMapping
	public ResponseEntity<Autor> insert(@RequestBody Autor autor){
		autor = service.insert(autor);
		// Retorna o codigo 201 no banco
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).body(autor);
	}
	
	// Metodo que vai na camda service e chama o metodo delete para deletar um autor
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// Metodo que vai na camda service e chama o metodo update para atualizar um autor
	@PutMapping(value = "/{id}")
	public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
		
	}
	
}




























