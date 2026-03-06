package com.leandro.library_manager_JPA.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.leandro.library_manager_JPA.entities.Emprestimo;

public class EmprestimoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDate dataEmprestimo;
	private LocalDate dataPrevisaoEntrega;
	private LocalDate dataEntrega;
	private Long leitorId;// Mantemos apenas o ID para vincular
	private Long livroId;// Mantemos apenas o ID para vincular
	


	public EmprestimoDTO() {

	}

	public EmprestimoDTO(Emprestimo entity) {
		this.id = entity.getId();
		this.dataEmprestimo = entity.getDataEmprestimo();
		this.dataPrevisaoEntrega = entity.getDataPrevisaoEntrega();
		this.dataEntrega = entity.getDataEntrega();
		if(entity.getLeitor() != null)
			this.leitorId = entity.getLeitor().getId();
		if(entity.getLivro() != null)
			this.livroId = entity.getLivro().getId();
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}

	public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getLeitorId() {
		return leitorId;
	}

	public void setLeitorId(Long autorId) {
		this.leitorId = autorId;
	}

	public Long getLivroId() {
		return livroId;
	}

	public void setLivroId(Long livroId) {
		this.livroId = livroId;
	}

}
