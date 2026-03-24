package com.leandro.library_manager_JPA.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.library_manager_JPA.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	Optional<Autor> findByNomeIgnoreCase(String nome);
}
