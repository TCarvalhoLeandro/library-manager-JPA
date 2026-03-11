package com.leandro.library_manager_JPA.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leandro.library_manager_JPA.dtos.LivroDTO;
import com.leandro.library_manager_JPA.entities.Livro;
import com.leandro.library_manager_JPA.resources.exceptions.DatabaseException;
import com.leandro.library_manager_JPA.services.AutorService;
import com.leandro.library_manager_JPA.services.LivroService;

@Controller
@RequestMapping("/biblioteca")
public class LivroWebController {

	@Autowired
	private LivroService service;

	@Autowired
	private AutorService autorService;

	
	
	@GetMapping("/livros")
	public String listaLivros(Model model) {

		// Busca a lista (Confira se seu service retorna DTO ou Entidade)
		List<Livro> list = service.findAll();

		// Adiciona ao modelo para o HTML ler
		model.addAttribute("meusLivros", list);

		// Vai procurar o arquivo autores.html
		return "/livros";
	}

	
	// Rota para ABRIR o formulário vazio
	@GetMapping("/livros/novo")
	public String abrirFormulario(Model model) {
		LivroDTO dto = new LivroDTO();
		model.addAttribute("livroDTO", dto);

		// Busca todos os autores e joga no "Model" com o nome "listaAutores"
		model.addAttribute("listaAutores", autorService.findAll());

		return "formulario_livro";
	}

	// Rota para receber os dados do botao salvar
	@PostMapping("/livros/salvar")
	public String salvarLivro(@ModelAttribute("livroDTO") LivroDTO dto) {
		service.insert(dto);
		return "redirect:/biblioteca/livros";
	}


	@GetMapping("/livros/deletar/{id}")
	public String deletarLivro(@PathVariable Long id, RedirectAttributes attributes) {
		try {
			service.delete(id);
			attributes.addFlashAttribute("mensagemSucesso", "Livro excluído com sucesso!");
		} catch (DatabaseException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/biblioteca/livros";
	}


	@GetMapping("/livros/editar/{id}")
	public String editarLivro(@PathVariable Long id, Model model) {
		LivroDTO dto = new LivroDTO(service.findById(id));

		model.addAttribute("livroDTO", dto);
		model.addAttribute("listaAutores", autorService.findAll()); // <--- IMPORTANTÍSSIMO

		return "formulario_livro";
	}

	// Salvar alterações
	@PostMapping("/livros/editar/{id}")
	public String atualizarLivro(@PathVariable Long id, @ModelAttribute("livroDTO") LivroDTO dto) {
		service.update(id, dto);
		return "redirect:/biblioteca/livros";
	}
}
