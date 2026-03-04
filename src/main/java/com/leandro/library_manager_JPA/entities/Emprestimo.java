package com.leandro.library_manager_JPA.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
    @Column(nullable = false)// Obriga a criar um Emprestimo com uma data diferente de null
	private LocalDate dataEmprestimo;
	
 
    @Column(nullable = false)// Obriga a criar um Emprestimo com uma data de previsao de entrega diferente de null
	private LocalDate dataPrevisaoEntrega;
	
	private LocalDate dataEntrega;
	
	@ManyToOne // Relacionamento: quem pegou?
	@JoinColumn(name = "leitor_id", nullable = false)
	private Leitor leitor;
	
	@ManyToOne// Relacionamento: o que foi pego?
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;
	
	public Emprestimo() {
		
	}

	
	// Construtor com logica de negocio
	public Emprestimo(Long id,Leitor leitor, Livro livro) {
		this.Id = id;
		this.dataEmprestimo = LocalDate.now();// data de hoje
		this.dataPrevisaoEntrega = LocalDate.now().plusDays(15);// prazo de 15 dias para entrega
		this.dataEntrega = null;// comeca como nao devolvido
		this.leitor = leitor;
		this.livro = livro;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
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


	public Leitor getLeitor() {
		return leitor;
	}


	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}


	public Livro getLivro() {
		return livro;
	}


	public void setLivro(Livro livro) {
		this.livro = livro;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(Id, other.Id);
	}
	
	
	// Método utilitário para saber se está atrasado
	public boolean EntregaAtrasada() {
		if(dataEntrega != null) {
			return false;// Já devolveu, não está atrasado
		}
		return LocalDate.now().isAfter(dataPrevisaoEntrega);
	}
	
	
	

}
