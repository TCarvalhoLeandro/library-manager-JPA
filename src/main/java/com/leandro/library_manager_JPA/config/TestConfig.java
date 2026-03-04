package com.leandro.library_manager_JPA.config;


import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leandro.library_manager_JPA.entities.Autor;
import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.entities.Leitor;
import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.repositories.AutorRepository;
import com.leandro.library_manager_JPA.repositories.EmprestimoRepository;
import com.leandro.library_manager_JPA.repositories.LeitorRepository;
import com.leandro.library_manager_JPA.repositories.LivroRepository;

/*Essa classe de configuração vai servir para poularmos o banco de teste H@*/


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired  // Injeção de dependencia
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private LeitorRepository leitorRepository;
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Autor a = new Autor("Machado de Assis", "Brasileiro", LocalDate.of(1839, 6, 21));
		Autor b = new Autor("Clarice Linspector", "Brasileira", LocalDate.of(1920, 12, 10) );
		
		autorRepository.saveAll(Arrays.asList(a,b));
		
		Livro l1 = new Livro(null, "Dom Casmurro", "978-8532515087", 1899, true, a);
		Livro l2 = new Livro(null, "Memórias Póstumas de Brás Cubas", "978-8532531391", 1881, true, a);
		Livro l3 = new Livro(null, "A Hora da Estrela", "978-8532530660" , 1977, true, b);
		Livro l4 = new Livro(null, "Perto do Coração Selvagem", "978-8532517524" , 1943, true, b);
		
		livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4));
		
		Leitor lt1 = new Leitor(null, "Leandro Carvalho", "05168935729", "Rua Caruso 64", LocalDate.of(1979, 03, 26));
		Leitor lt2 = new Leitor(null, "Wanja Carvalho", "12345678909", "Rua Caruso 64", LocalDate.of(1976, 06, 8));
		Leitor lt3 = new Leitor(null, "Lucas Carvalho", "98745632101", "Rua Caruso 64", LocalDate.of(2009, 2, 26));
		Leitor lt4 = new Leitor(null, "Arthur  Carvalho", "45678932121", "Rua Caruso 64", LocalDate.of(2012, 07, 19));
		
		leitorRepository.saveAll(Arrays.asList(lt1,lt2,lt3,lt4));
		
		Emprestimo e1 = new Emprestimo(null, lt1, l2);
		Emprestimo e2 = new Emprestimo(null, lt1, l4);
		Emprestimo e3 = new Emprestimo(null, lt3, l3);
		Emprestimo e4 = new Emprestimo(null, lt4, l1);
		Emprestimo e5 = new Emprestimo(null, lt2, l2);
		
		emprestimoRepository.saveAll(Arrays.asList(e1,e2,e3,e4,e5));
		
	}

	
}

/*implements CommandLineRunner -> Macete para executar os comandos dentro de run() quando o programa inicia*/
