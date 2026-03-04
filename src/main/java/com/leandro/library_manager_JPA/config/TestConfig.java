package com.leandro.library_manager_JPA.config;


import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leandro.library_manager_JPA.entities.Autor;
import com.leandro.library_manager_JPA.repositories.AutorRepository;

/*Essa classe de configuração vai servir para poularmos o banco de teste H@*/


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired  // Injeção de dependencia
	private AutorRepository autorRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Autor a = new Autor("Machado de Assis", "Brasileiro", LocalDate.of(1839, 6, 21));
		Autor b = new Autor("Clarice Linspector", "Brasileira", LocalDate.of(1920, 12, 10) );
		
		autorRepository.saveAll(Arrays.asList(a,b));
		
	}
	
	
	
}

/*implements CommandLineRunner -> Macete para executar os comandos dentro de run() quando o programa inicia*/
