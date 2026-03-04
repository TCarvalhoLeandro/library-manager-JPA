package com.leandro.library_manager_JPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.library_manager_JPA.entities.Leitor;

public interface LeitorRepository extends JpaRepository<Leitor, Long>{

}
