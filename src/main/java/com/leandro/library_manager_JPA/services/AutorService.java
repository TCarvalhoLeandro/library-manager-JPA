package com.leandro.library_manager_JPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.leandro.library_manager_JPA.entities.Autor;
import com.leandro.library_manager_JPA.repositories.AutorRepository;

@Service // Registra essa classe como componente do spring
public class AutorService {
	
	@Autowired
	private AutorRepository repository; // Dependencia de AutorRepository
	
	// Metodo que vai na camada repository busca todos os autores e retorna pra resource
	public List<Autor> findAll(){
		return repository.findAll();
	}
	
	// Metodo que vai na camada repository busca autor por id retorna pra resource
	public Autor findById(Long id) {
		Optional<Autor> obj = repository.findById(id);
		return obj.get();
	}
	
	// Metodo para inserir no banco de dados um novo objeto do tipo Autor
	public Autor insert(Autor autor) {
		return repository.save(autor);
	}
	
	// Metodo para deletar do banco de dados um objeto do tipo Autor
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	// Metodo para atualizar no banco de dados um objeto do tipo Autor
	public Autor update(Long id, Autor autorNovo) {
		Autor autor = repository.getReferenceById(id);
		updateData(autor, autorNovo);
		return repository.save(autor);
	}

	private void updateData(Autor autor, Autor autorNovo) {
		autor.setNome(autorNovo.getNome());
		autor.setNacionalidade(autorNovo.getNacionalidade());
		autor.setDataNascimento(autorNovo.getDataNascimento());	
	}
	
	
}








