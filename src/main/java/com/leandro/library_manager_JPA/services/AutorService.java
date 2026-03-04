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
}
