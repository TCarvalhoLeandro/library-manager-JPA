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

import com.leandro.library_manager_JPA.dtos.LeitorDTO;

import com.leandro.library_manager_JPA.entities.Leitor;

import com.leandro.library_manager_JPA.services.LeitorService;

@RestController
@RequestMapping(value = "/leitores")
public class LeitorResource {

	@Autowired
	private LeitorService service;

	/*
	 * 1 Metodo que chama service para retornar todos os Livros do repository
	 * (banco) para uma requisição web
	 */
	@GetMapping
	public ResponseEntity<List<Leitor>> findAll() {
		List<Leitor> leitores = service.findAll();
		return ResponseEntity.ok().body(leitores);
	}

	/*
	 * 2 Metodo que chama service para retornar um Leitor por id do repository
	 * (banco) para uma requisição web
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Leitor> findById(@PathVariable Long id) {
		Leitor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * 3 Metodo que vai na camada service e chama o metodo insert para inserir um
	 * Leitor Aqui o JSON CHEGA. O Jackson usa o construtor VAZIO.
	 */
	@PostMapping
	public ResponseEntity<Leitor> insert(@RequestBody LeitorDTO dto) {

		Leitor obj = service.insert(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	/*
	 * 4 Metodo que vai na camada service e chama o metodo delete para deletar um
	 * Leitor Como o Livro é o lado fraco da associação pode ser deletado que o
	 * Autor continua exixtindo
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Leitor> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * 5 Metodo que vai na camada service e chama o metodo update para atualizar um
	 * Livro
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<LeitorDTO> update(@PathVariable Long id, @RequestBody LeitorDTO obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);

	}

}
