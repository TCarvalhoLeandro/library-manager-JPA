package com.leandro.library_manager_JPA.dtos;

import com.leandro.library_manager_JPA.entities.Livro;

public class LivroDTO {

	private String titulo;
	private Integer quantidade;
	private String isbn;
	private Integer anoPublicacao;
	private boolean disponivel;
	
	// A GRANDE DIFERENÇA
    // Na Entidade, isso é: private Autor autor;
    // No DTO, nós simplificamos para:
	private Long autorId;// Mantemos apenas o ID para vincular
	
	public LivroDTO() {
		
	}

	public LivroDTO(String titulo, Integer quantidade, String isbn, Integer anoPublicacao, boolean disponivel, Long autorId) {
		this.titulo = titulo;
		this.quantidade = quantidade;
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
		this.disponivel = disponivel;
		this.autorId = autorId;
	}
	
	
	// 3. Construtor que recebe a Entidade (Dica de Ouro do Nélio Alves)
    // Isso facilita muito na hora de converter Livro -> LivroDTO para devolver no GET
	public LivroDTO(Livro entity) {
		this.titulo = entity.getTitulo();
		this.isbn = entity.getIsbn();
		this.anoPublicacao = entity.getAnoPublicacao();
		if(entity.getAutor() != null)
			this.autorId = entity.getAutor().getId();
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
	
	
}
