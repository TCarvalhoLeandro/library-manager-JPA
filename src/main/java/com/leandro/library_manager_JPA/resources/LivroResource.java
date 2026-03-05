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

import com.leandro.library_manager_JPA.dtos.LivroDTO;
import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;

	// 1 Metodo que chama service para retornar todos os livros do repository
	// (banco)
	// para uma requisição web
	@GetMapping
	public ResponseEntity<List<Livro>> findAll() {
		List<Livro> livro = service.findAll();
		return ResponseEntity.ok().body(livro);
	}

	// 2 Metodo que chama service para retornar um livro por id do repository
	// (banco)
	// para uma requisição web
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		Livro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// 3 Metodo que vai na camada service e chama o metodo insert para inserir um
	// Livro
	@PostMapping
	public ResponseEntity<Livro> insert(@RequestBody LivroDTO dto) {

		Livro livro = service.insert(dto);
		// Retorna o codigo 201 no banco
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).body(livro);
	}

	// 4 Metodo que vai na camada service e chama o metodo delete para deletar um
	// Livro
	// Como o Livro é o lado fraco da associação pode ser deletado que o Autor
	// continua exixtindo
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Livro> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// 5
	// Metodo que vai na camada service e chama o metodo update para atualizar um
	// Livro
	@PutMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Long id, @RequestBody LivroDTO obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);

	}

}
