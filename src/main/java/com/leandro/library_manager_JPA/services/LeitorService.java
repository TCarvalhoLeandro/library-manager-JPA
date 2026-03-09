package com.leandro.library_manager_JPA.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.library_manager_JPA.dtos.LeitorDTO;
import com.leandro.library_manager_JPA.entities.Leitor;
import com.leandro.library_manager_JPA.repositories.LeitorRepository;
import com.leandro.library_manager_JPA.services.exceptions.ResourceNotFoundException;

@Service
public class LeitorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private LeitorRepository repository;

	/*
	 * 1 Metodo que vai na camada repository busca todos os leitores e retorna pra
	 * resource
	 */
	public List<Leitor> findAll() {
		return repository.findAll();
	}

	/*
	 * 2 Metodo que vai na camada repository busca leitor por id retorna pra
	 * resource
	 */
	public Leitor findById(Long id) {
		Optional<Leitor> obj = repository.findById(id);
		return obj.get();
	}

	/*
	 * 3 Metodo para inserir no banco de dados um novo objeto do tipo Leitor
	 */
	public Leitor insert(LeitorDTO dto) {

		Leitor obj = new Leitor();
		obj.setNome(dto.getNome());
		obj.setTelefone(dto.getTelefone());
		obj.setEndereco(dto.getEndereco());
		obj.setDataNascimento(dto.getDataNascimento());

		return repository.save(obj);
	}

	/*
	 * 4 Metodo para deletar do banco de dados um objeto do tipo Livro
	 */
	public void delete(Long id) {
		if (!repository.existsById(id))
			throw new ResourceNotFoundException(id);

		repository.deleteById(id);

	}

	/*
	 * 5 Metodo para atualizar no banco de dados um objeto do tipo Livro
	 */
	public LeitorDTO update(Long id, LeitorDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		// Instancia o Livro monitorado pelo JPA sem ir no banco (Performance)
		Leitor obj = repository.getReferenceById(id);

		// Atualiza os dados do Livro com o que veio no DTO
		updateData(obj, dto);

		// Salva a atualização
		obj = repository.save(obj);

		// Retorna o DTO atualizado
		return new LeitorDTO(obj);
	}

// Método auxiliar
	private void updateData(Leitor entity, LeitorDTO dto) {
		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity.setEndereco(dto.getEndereco());
		entity.setDataNascimento(dto.getDataNascimento());

	}

}
