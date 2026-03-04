package com.leandro.library_manager_JPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.repositories.EmprestimoRepository;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository repository;
	
	public List<Emprestimo> findAll(){
		return repository.findAll();
	}
	
	// Metodo que vai na camada repository busca Emprestimo por id retorna pra resource
			public Emprestimo findById(Long id) {
				Optional<Emprestimo> obj = repository.findById(id);
				return obj.get();
			}
}
