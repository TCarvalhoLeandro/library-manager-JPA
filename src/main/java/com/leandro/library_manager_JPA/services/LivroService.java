package com.leandro.library_manager_JPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	public List<Livro> findAll(){
		return repository.findAll();
	}
	
	public Livro findById(Long id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.get();
	}

}
