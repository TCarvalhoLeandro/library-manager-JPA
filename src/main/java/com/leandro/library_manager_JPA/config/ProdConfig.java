package com.leandro.library_manager_JPA.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leandro.library_manager_JPA.dtos.EmprestimoDTO;
import com.leandro.library_manager_JPA.dtos.LivroDTO;
import com.leandro.library_manager_JPA.entities.Autor;
import com.leandro.library_manager_JPA.entities.Leitor;
import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.repositories.AutorRepository;
import com.leandro.library_manager_JPA.repositories.LeitorRepository;
import com.leandro.library_manager_JPA.services.EmprestimoService;
import com.leandro.library_manager_JPA.services.LivroService;

/*Essa classe de configuração vai servir para poularmos o banco Neon*/

@Configuration
@Profile("prod")
public class ProdConfig implements CommandLineRunner {

	// Vamos usar Repositories para os "Pais" (Autor/Leitor) para ser mais direto
	@Autowired // Injeção de dependencia
	private AutorRepository autorRepository;

	@Autowired
	private LeitorRepository leitorRepository;

	@Autowired
	private LivroService livroService;

	@Autowired
	private EmprestimoService emprestimoService;

	@Override
	public void run(String... args) throws Exception {
		
		//TRAVA DE SEGURANÇA (Para não duplicar dados no banco da nuvem)
        if (autorRepository.count() > 0) {
            System.out.println("--- BANCO DE DADOS JÁ POPULADO. INICIALIZAÇÃO CANCELADA. ---");
            return;
        }

		// 1: CRIAR AUTORES (Base)
		Autor a = new Autor("Machado de Assis", "Brasileiro", LocalDate.of(1839, 6, 21));
		Autor b = new Autor("Clarice Linspector", "Brasileira", LocalDate.of(1920, 12, 10));

		autorRepository.saveAll(Arrays.asList(a, b));

		// 2: CRIAR LEITORES (Base)
		Leitor lt1 = new Leitor(null, "Leandro Carvalho", "05168935729", "Rua Caruso 64", LocalDate.of(1979, 03, 26));
		Leitor lt2 = new Leitor(null, "Wanja Carvalho", "12345678909", "Rua Caruso 64", LocalDate.of(1976, 06, 8));
		Leitor lt3 = new Leitor(null, "Lucas Carvalho", "98745632101", "Rua Caruso 64", LocalDate.of(2009, 2, 26));
		Leitor lt4 = new Leitor(null, "Arthur  Carvalho", "45678932121", "Rua Caruso 64", LocalDate.of(2012, 07, 19));

		leitorRepository.saveAll(Arrays.asList(lt1, lt2, lt3, lt4));

		// PASSO 3: CRIAR LIVROS VIA SERVICE (Simulando o Front-end)
		LivroDTO dto1 = new LivroDTO();
		dto1.setTitulo("Dom Casmurro");
		dto1.setQuantidade(10);
		dto1.setIsbn("978-8532515087");
		dto1.setAnoPublicacao(1899);
		dto1.setDisponivel(true);
		dto1.setAutorId(a.getId());

		LivroDTO dto2 = new LivroDTO();
		dto2.setTitulo("A Hora da Estrela");
		dto2.setQuantidade(10);
		dto2.setIsbn("978-8532530660");
		dto2.setAnoPublicacao(1977);
		dto2.setDisponivel(true);
		dto2.setAutorId(b.getId());

		// O Service vai salvar, converter e devolver a Entidade pronta
		// Importante: Aqui usamos o service.insert para ele já validar regras se
		// existirem
		Livro livroSalvo1 = livroService.insert(dto1);
		Livro livroSalvo2 = livroService.insert(dto2);

		// 4: CRIAR EMPRÉSTIMOS VIA SERVICE (A Grande Prova)
		EmprestimoDTO e1 = new EmprestimoDTO();
		e1.setLeitorId(lt1.getId());
		e1.setLivroId(livroSalvo1.getId());// Pega o ID do Dom Casmurro salvo
		
		EmprestimoDTO e2 = new EmprestimoDTO();
		e2.setLeitorId(lt3.getId());
		e2.setLivroId(livroSalvo2.getId());// Pega o ID do A Hora da Estrela salvo

		

		// CHAMADA MÁGICA:
		// Isso vai:
		// 1. Validar se o livro existe
		// 2. Validar se está disponível
		// 3. Setar Data Emprestimo (Hoje) e Previsão (Hoje + 7)
		// 4. Salvar Empréstimo
		// 5. Mudar status do Livro para Indisponível (Update automático)
		emprestimoService.insert(e1);
		emprestimoService.insert(e2);

	}

}

/*
 * implements CommandLineRunner -> Macete para executar os comandos dentro de
 * run() quando o programa inicia
 */
