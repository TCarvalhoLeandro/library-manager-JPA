package com.leandro.library_manager_JPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.entities.Leitor;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
	//// O Spring traduz isso para: SELECT COUNT(*) FROM tb_emprestimo WHERE leitor_id = ? AND data_entrega IS NULL
	long countByLeitorAndDataEntregaIsNull(Leitor leitor);
}

