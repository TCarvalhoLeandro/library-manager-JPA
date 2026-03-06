package com.leandro.library_manager_JPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.library_manager_JPA.dtos.LivroDTO;
import com.leandro.library_manager_JPA.entities.Autor;
import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.repositories.AutorRepository;
import com.leandro.library_manager_JPA.repositories.LivroRepository;
import com.leandro.library_manager_JPA.services.exceptions.ResourceNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	/*
		1 Metodo que vai na camada repository busca todos os livros e retorna pra resource
	*/
	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	/*
		2 Metodo que vai na camada repository busca livro por id retorna pra resource
	*/
	public Livro findById(Long id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.get();
	}
	/*
	 	3 Metodo para inserir no banco de dados um novo objeto do tipo Livro
	 */
	public Livro insert(LivroDTO livroDTO) {
		// Busca o Autor pelo ID que veio no DTO se nao encontrar lança uma exceção
		Autor obj = autorRepository.findById(livroDTO.getAutorId())
				.orElseThrow(() -> new ResourceNotFoundException("Author not found."));

		// Cria o Livro e associa o objeto Autor recuperado
		Livro livro = new Livro();
		livro.setTitulo(livroDTO.getTitulo());
		livro.setQuantidade(livroDTO.getQuantidade());
		livro.setIsbn(livroDTO.getIsbn());
		livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
		livro.setDisponivel(true);
		livro.setAutor(obj);

		return livroRepository.save(livro);
	}

	/*
	  	4 Metodo para deletar do banco de dados um objeto do tipo Livro
	 */
	public void delete(Long id) {
		if (!livroRepository.existsById(id))
			throw new ResourceNotFoundException(id);

		livroRepository.deleteById(id);

	}

	/*
	 	5 Metodo para atualizar no banco de dados um objeto do tipo Livro
	 */
	public LivroDTO update(Long id, LivroDTO livroDTO) {
		if (!livroRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		//Instancia o Livro monitorado pelo JPA sem ir no banco (Performance)
		Livro livro = livroRepository.getReferenceById(id);
		
		//Atualiza os dados do Livro com o que veio no DTO
		updateData(livro, livroDTO);
		
		//Salva a atualização
		livro = livroRepository.save(livro);
		
		//Retorna o DTO atualizado
		return new LivroDTO(livro);
	}
	
	// Método auxiliar
	private void updateData(Livro livro, LivroDTO livroDTO) {
		livro.setTitulo(livroDTO.getTitulo());
		livro.setQuantidade(livroDTO.getQuantidade());
		livro.setIsbn(livroDTO.getIsbn());
		livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
		livro.setDisponivel(livroDTO.getDisponivel());
		
		if(livroDTO.getAutorId() != null) {
			Autor autor = autorRepository.getReferenceById(livroDTO.getAutorId());
			livro.setAutor(autor);
		}
	}

}














