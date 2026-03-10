package com.leandro.library_manager_JPA.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.leandro.library_manager_JPA.entities.Livro;

public class LivroDTO {

	private Long id;
	private String titulo;
	private Integer quantidade;
	private String isbn;
	private Integer anoPublicacao;
	private boolean disponivel;

	private Long autorId;
	
	private String novoAutorNome;
    private String novoAutorNacionalidade;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate novoAutorNascimento;
	
	public LivroDTO() {
		
	}
	
	// Construtor que recebe a Entidade e preenche o DTO
	public LivroDTO(Livro entity) {
	    this.id = entity.getId();
	    this.titulo = entity.getTitulo();
	    this.isbn = entity.getIsbn();
	    this.quantidade = entity.getQuantidade();
	    this.anoPublicacao = entity.getAnoPublicacao();
	    
	    // O PULO DO GATO: Pega o ID do autor para o dropdown saber quem selecionar
	    if (entity.getAutor() != null) {
	        this.autorId = entity.getAutor().getId();
	        //this.nomeAutor = entity.getAutor().getNome(); // Opcional, se quiser mostrar o nome
	    }
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public boolean getDisponivel() {
		return disponivel;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}


	public String getNovoAutorNome() {
		return novoAutorNome;
	}


	public void setNovoAutorNome(String novoAutorNome) {
		this.novoAutorNome = novoAutorNome;
	}


	public String getNovoAutorNacionalidade() {
		return novoAutorNacionalidade;
	}


	public void setNovoAutorNacionalidade(String novoAutorNacionalidade) {
		this.novoAutorNacionalidade = novoAutorNacionalidade;
	}


	public LocalDate getNovoAutorNascimento() {
		return novoAutorNascimento;
	}


	public void setNovoAutorNascimento(LocalDate novoAutorNascimento) {
		this.novoAutorNascimento = novoAutorNascimento;
	}
	
	
}
