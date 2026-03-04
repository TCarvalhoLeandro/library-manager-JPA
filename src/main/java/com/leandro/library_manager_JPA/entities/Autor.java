package com.leandro.library_manager_JPA.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_autor")
public class Autor  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)// nao permite que a coluna seja nula e com menos de 100 caracteres
	private String nome;
	
	// Relacionamento Inverso: Um autor tem VÁRIOS livros
    // "mappedBy" avisa que quem manda na relação é o atributo "autor" lá na classe Livro
	@JsonIgnore
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)// Inicializa a lista para evitar NullPointerException
	private List<Livro> livros = new ArrayList<>();
	
	
	private String nacionalidade;
	private LocalDate dataNascimento;
	
	public Autor() {
		
	}

	public Autor(String nome, String nacionalidade, LocalDate dataNascimento) {
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivros() {
		return livros;
	}
    
	// Em uma coleção só pode o get
	
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
