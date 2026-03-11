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

import com.leandro.library_manager_JPA.dtos.EmprestimoDTO;
import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.services.EmprestimoService;
import com.leandro.library_manager_JPA.services.LeitorService;
import com.leandro.library_manager_JPA.services.LivroService;
import com.leandro.library_manager_JPA.services.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/biblioteca")
public class EmprestimoWebController {

	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	private LivroService livroService;

	@Autowired
	private LeitorService leitorService;

	@GetMapping("/emprestimos")
	public String listaEmprestimo(Model model) {

		List<Emprestimo> list = emprestimoService.findAll();

		model.addAttribute("meusEmprestimos", list);

		return "emprestimos";
	}

	// Método para ABRIR A TELA de formulário
	@GetMapping("/emprestimos/novo")
	public String formNovoEmprestimo(Model model) {

		// Cria um objeto vazio para o formulário preencher
		model.addAttribute("emprestimoDTO", new EmprestimoDTO());

		// Manda a lista de leitores e livros para montar as caixas de seleção
		// (<select>)
		model.addAttribute("leitores", leitorService.findAll());
		model.addAttribute("livros", livroService.findAll());

		// Retorna o nome do arquivo HTML (ex: formulario-emprestimo.html)
		return "formulario_emprestimo";

	}

	// Método para SALVAR o formulario quando o usuario clicar em "Enviar"
	@PostMapping("/emprestimos/salvar")
	public String salvarEmprestimo(@ModelAttribute EmprestimoDTO dto, RedirectAttributes attributes) {

		try {
			emprestimoService.insert(dto);

			// Se passar da linha acima sem erros, prepara uma mensagem de sucesso
			attributes.addFlashAttribute("mensagemSucesso", "Empréstimo realizado com sucesso!");

			// Redireciona para a tabela (lista) de empréstimos
			return "redirect:/biblioteca/emprestimos";
			
		} catch (IllegalArgumentException | ResourceNotFoundException e) {
			// Se o livro estiver indisponível (IllegalArgument) ou os IDs não existirem
			// (NotFound), cai aqui!
			// Pegamos a mensagem de erro exata que você escreveu no Service ("The book has
			// already been borrowed!")
			attributes.addFlashAttribute("mensagemErro", e.getMessage());

			// Redireciona o usuário de volta para a tela do formulário para ele tentar
			// outro livro
			return "redirect:/biblioteca/emprestimos/novo";
		}

	}
	
	// Metodo para receber o clique do botão "Devolver" que vamos colocar no HTML
	@GetMapping("/emprestimos/devolver/{id}")
	public String devolverEmprestimo(@PathVariable Long id, RedirectAttributes attributes) {
		
		try {
			emprestimoService.devolucao(id);
			
			attributes.addFlashAttribute("mensagemSucesso", "Livro devolvido com sucesso!");
		}
		catch(IllegalArgumentException | ResourceNotFoundException e) {
			
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		
		return "redirect:/biblioteca/emprestimos";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
