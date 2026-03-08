package com.leandro.library_manager_JPA.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.services.LivroService;

@Controller
@RequestMapping("/biblioteca")
public class LivroWebController {

	
	@Autowired
	private LivroService service;
	
	@GetMapping("/livros")
	public String listaLivros(Model model) {
		
		// Busca a lista (Confira se seu service retorna DTO ou Entidade)
		List<Livro> list = service.findAll();
		
		// Adiciona ao modelo para o HTML ler
		model.addAttribute("meusLivros", list);
		
		// Vai procurar o arquivo autores.html
		return "/livros";
	}
}
