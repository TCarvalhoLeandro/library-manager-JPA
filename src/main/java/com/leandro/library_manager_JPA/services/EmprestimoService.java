package com.leandro.library_manager_JPA.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.library_manager_JPA.dtos.EmprestimoDTO;
import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.entities.Leitor;
import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.repositories.EmprestimoRepository;
import com.leandro.library_manager_JPA.repositories.LeitorRepository;
import com.leandro.library_manager_JPA.repositories.LivroRepository;
import com.leandro.library_manager_JPA.resources.exceptions.DatabaseException;
import com.leandro.library_manager_JPA.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository repository;

	@Autowired
	private LeitorRepository leitorRepository;

	@Autowired
	private LivroRepository livroRepository;

	
	/*
		1 Metodo que vai na camada repository busca todos os Emprestimos e retorna pra resource
	 */
	public List<Emprestimo> findAll() {
		return repository.findAll();
	}

	/*
		2 Metodo que vai na camada repository busca Emprestimo por id retorna pra resource
	 */
	public Emprestimo findById(Long id) {
		Optional<Emprestimo> obj = repository.findById(id);
		return obj.get();
	}

	/*
 		3 Metodo para inserir no banco de dados um novo objeto do tipo Emprestimo
	 */
	//Recebe os dados simples (DTO) e promete devolver o Empréstimo completo.
	@Transactional
	public Emprestimo insert(EmprestimoDTO empDTO) {
		
		// 1. BUSCA O LEITOR (Validação de Existência)
	    // Vai no banco (tabela leitor). Se achar, guarda na variável 'leitor'.
	    // Se não achar (ID inválido), lança erro 404 e para a execução imediatamente.
		Leitor leitor = leitorRepository.findById(empDTO.getLeitorId())
				.orElseThrow(() -> new ResourceNotFoundException("Reader not found."));
		
		// 2. BUSCA O LIVRO (Validação de Existência)
	    // Vai no banco (tabela livro). Se achar, guarda na variável 'livro'.
	    // Se não achar, lança erro 404.
		Livro livro = livroRepository.findById(empDTO.getLivroId())
				.orElseThrow(() -> new ResourceNotFoundException("Book not found."));
		
		
		
		// 3. REGRA DE NEGÓCIO (Disponibilidade)
	    // Verifica na memória se a variável 'disponivel' do livro é falsa.
	    if (!livro.isDisponivel()) {
	    	// Se o livro já estiver emprestado, lança um erro (500) para impedir a operação.
	         throw new RuntimeException("The book has already been borrowed!");
	    }
		
	    // 4. PREPARAÇÃO (Instanciação)
	    // Cria um objeto Emprestimo vazio na memória do Java para começarmos a preencher.
		Emprestimo emp = new Emprestimo();
		
		// 5. DEFINIÇÃO DE DATAS (Automação)
	    // Define a data de início como "Agora" (Data do servidor).
		emp.setDataEmprestimo(LocalDate.now());
		
		// Calcula a data de entrega somando 7 dias à data de hoje (Regra da Biblioteca).
		emp.setDataPrevisaoEntrega(LocalDate.now().plusDays(7));
		
		// Define explicitamente que o livro ainda está com o leitor (Data real de entrega é nula).
		emp.setDataEntrega(null);
		
		// 6. ASSOCIAÇÃO (Chaves Estrangeiras)
	    // Vincula o Objeto Leitor encontrado (passo 1) ao empréstimo.
		emp.setLeitor(leitor);
		
		// Vincula o Objeto Livro encontrado (passo 2) ao empréstimo.
		emp.setLivro(livro);
		
		// 7. PERSISTÊNCIA (Insert)
	    // O Hibernate salva o objeto 'emp' na tabela 'tb_emprestimo'.
	    // O objeto 'emp' recebe o ID gerado pelo banco.
		emp = repository.save(emp);
		
		// 8. ATUALIZAÇÃO DO LIVRO (Mudança de Estado)
	    // Altera o status do objeto 'livro' na memória para "indisponível".
		livro.setDisponivel(false);
		
		// 9. PERSISTÊNCIA (Update)
	    // O Hibernate salva essa alteração na tabela 'tb_livro'. 
	    // Isso garante que ninguém mais consiga pegar esse livro.
		livroRepository.save(livro);
		
		// 10. RETORNO
	    // Devolve o recibo completo do empréstimo para quem chamou o método.
		return emp;
		
	}
	
	/*
 		 4 Metodo para atualizar no banco de dados um objeto do tipo EMprestimo
	*/
	@Transactional
	public void devolucao(Long id) {
		
		// Busca um Emprestimo pelo id
		Emprestimo emp = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Emprestimo não encontrado"));
		
		// Verifica se ja nao foi devolvido antes
		if(emp.getDataEntrega() != null) {
			throw new IllegalArgumentException("Livro já devolvido!");
		}
		
		// Atualiza a data de entrega para hoje
		emp.setDataEntrega(LocalDate.now());
		
		// Muda o status do livro para disponivel (true)
		emp.getLivro().setDisponivel(true);
		
		// O @Transactional faz o UPDATE automático tanto na tabela tb_emprestimo 
	    // quanto na tabela tb_livro ao terminar o método!
		
		livroRepository.save(emp.getLivro()); // Salva o Livro como disponível
	    repository.save(emp);
	}
	
	
	
	
	
	/*
	public EmprestimoDTO update(Long id) {
		// Se o Id do Emprestimo nao existir lança ResourceNotFoundException
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		
		//Instancia o Emprestimo monitorado pelo JPA sem ir no banco (Performance)
		Emprestimo emp = repository.getReferenceById(id);
		
		//Atualiza os dados do Livro com o que veio no DTO
		updateData(emp);
		
		//Salva a atualização
		emp = repository.save(emp);
		
		//Retorna o DTO atualizado
		return new EmprestimoDTO(emp);
		
	}
	
	// So vou atualizar a data de entrega e a disponibilidade do livro
	private void updateData(Emprestimo emp) {
		emp.setDataEntrega(LocalDate.now());
		emp.getLivro().setDisponivel(true);
	}
	*/
}











