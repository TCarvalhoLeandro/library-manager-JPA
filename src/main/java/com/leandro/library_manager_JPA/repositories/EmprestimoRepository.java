package com.leandro.library_manager_JPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.library_manager_JPA.entities.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

}
