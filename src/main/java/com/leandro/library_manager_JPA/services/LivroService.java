package com.leandro.library_manager_JPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.leandro.library_manager_JPA.dtos.LivroDTO;
import com.leandro.library_manager_JPA.entities.Autor;
import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.repositories.AutorRepository;
import com.leandro.library_manager_JPA.repositories.LivroRepository;
import com.leandro.library_manager_JPA.resources.exceptions.DatabaseException;
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
	public Livro insert(LivroDTO dto) {
		Autor autorFinal = new Autor();
		
		//Usuario selecionou um ID no dropdown
		if(dto.getAutorId() != null) {
			autorFinal = autorRepository.findById(dto.getAutorId())
					.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado no banco!"));
		}
		
		//Usuario nao selecionou ID, mas preencheu o Nome do novo autor
		else if(dto.getNovoAutorNome() != null && !dto.getNovoAutorNome().isEmpty()) {
			Autor novoAutor = new Autor();
			novoAutor.setNome(dto.getNovoAutorNome());
			novoAutor.setNacionalidade(dto.getNovoAutorNacionalidade());
			novoAutor.setDataNascimento(dto.getNovoAutorNascimento());
			
			// salva o Autor primeiro para gerar o ID
			autorFinal = autorRepository.save(novoAutor);
	
		}
		// nao fez nem um nem outro
		else {
			throw new DatabaseException("Nenhum Autor foi associado!");
		}
		
		// Agora cria o Livro e vincula ao autor decidido acima
		Livro livro = new Livro();
		livro.setTitulo(dto.getTitulo());
		livro.setIsbn(dto.getIsbn());
		livro.setQuantidade(dto.getQuantidade());
		livro.setAnoPublicacao(dto.getAnoPublicacao());
		livro.setDisponivel(true);
		
		livro.setAutor(autorFinal);
		
		return livroRepository.save(livro);
	}

	/*
	  	4 Metodo para deletar do banco de dados um objeto do tipo Livro
	 */
	// Método DELETE (Cuidado com livros emprestados!)
	public void delete(Long id) {
		if (!livroRepository.existsById(id))
			throw new ResourceNotFoundException(id);
		try {
			livroRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possível excluir Livro com empréstimo pendente!");
		}
		

	}

	/*
	 	5 Metodo para atualizar no banco de dados um objeto do tipo Livro
	*/
	public Livro update(Long id, LivroDTO dto) {
		// Busca o livro original no banco
		Livro entity = livroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		// Atualize os dados simples
		entity.setTitulo(dto.getTitulo());
		entity.setIsbn(dto.getIsbn());
		entity.setQuantidade(dto.getQuantidade());
		entity.setAnoPublicacao(dto.getAnoPublicacao());
		
		// Atualiza o Autor se o usuario nao mudou no dropdown
		if(dto.getAutorId() != null) {
			Autor autor = autorRepository.findById(dto.getAutorId())
					.orElseThrow(() -> new ResourceNotFoundException(id));
			entity.setAutor(autor);
		}
		
		return livroRepository.save(entity);
	}

}














