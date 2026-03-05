package com.leandro.library_manager_JPA.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.library_manager_JPA.entities.Leitor;
import com.leandro.library_manager_JPA.repositories.LeitorRepository;

@Service
public class LeitorService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LeitorRepository repository;
	
	public List<Leitor> findAll(){
		return repository.findAll();
	}
	
	// Metodo que vai na camada repository busca Leitor por id retorna pra resource
	public Leitor findById(Long id) {
		Optional<Leitor> obj = repository.findById(id);
		return obj.get();
	}

}
