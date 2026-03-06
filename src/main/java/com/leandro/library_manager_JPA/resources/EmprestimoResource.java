package com.leandro.library_manager_JPA.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leandro.library_manager_JPA.dtos.EmprestimoDTO;
import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.services.EmprestimoService;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource {

	@Autowired
	private EmprestimoService service;
	
	/*
		1 Metodo que chama service para retornar todos os emprestimos do repository (banco)
		para uma requisição web
	*/
	@GetMapping
	public ResponseEntity<List<Emprestimo>> findAll() {
		List<Emprestimo> emprestimo = service.findAll();
		return ResponseEntity.ok().body(emprestimo);
	}

	/*
		2 Metodo que chama service para retornar um Emprestimo por id do repository
		(banco) para uma requisição web
	*/
	@GetMapping(value = "/{id}")
	public ResponseEntity<Emprestimo> findById(@PathVariable Long id) {
		Emprestimo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
		3 Metodo que vai na camada service e chama o metodo insert para inserir um  Emprestimo
		Aqui o JSON CHEGA. O Jackson usa o construtor VAZIO.
	 */
	@PostMapping
	public ResponseEntity<EmprestimoDTO> insert(@RequestBody EmprestimoDTO empDTO) {
		// Chama o service (que retorna uma Entidade)
		Emprestimo emp = service.insert(empDTO);

		// Aqui o JSON SAI. VOCÊ usa o construtor COM ARGUMENTOS manualmente.
		// Você está pegando a Entidade cheia de dados do banco e transformando em DTO
		// para devolver.
		EmprestimoDTO newDTO = new EmprestimoDTO(emp);

		// Retorna o codigo 201 no banco
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emp.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);

	}
	
	/*
		4 Metodo que vai na camada service e chama o metodo update para atualizar um Emprestimo
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id){
		EmprestimoDTO emp = service.update(id);
		return ResponseEntity.ok().body(emp);
	}
	
}
// Por enquanto não vou deletar Emprestimos











