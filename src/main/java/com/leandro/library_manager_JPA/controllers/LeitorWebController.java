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

import com.leandro.library_manager_JPA.dtos.LeitorDTO;
import com.leandro.library_manager_JPA.entities.Leitor;
import com.leandro.library_manager_JPA.resources.exceptions.DatabaseException;
import com.leandro.library_manager_JPA.services.LeitorService;

@Controller
@RequestMapping("/biblioteca")
public class LeitorWebController {

	@Autowired
	private LeitorService service;
	
	
	@GetMapping("/leitores")
	public String listaLeitores(Model model) {
		List<Leitor> list = service.findAll();
		model.addAttribute("meusLeitores", list);	
		return "leitores";
	}
	
	// METODOS PARA INSERIR UM LEITOR
	//Rota para ABRIR o formulário vazio
	@GetMapping("/leitores/novo")
	public String abrirFormulario(Model model) {
		LeitorDTO dto = new LeitorDTO();
		model.addAttribute("leitorDTO", dto);
		return "formulario_leitor";// Nome do arquivo HTML que vamos criar
	}
	//Rota para receber os dados do botao salvar
	@PostMapping("/leitores/salvar")
	public String salvarLeitor(@ModelAttribute("leitorDTO") LeitorDTO dto) {
		service.insert(dto);
		return "redirect:/biblioteca/leitores";
	}
	
	
	//METODO PARA DELETAR UM LEITOR
	@GetMapping("/leitores/deletar/{id}")
	public String deletarLeitor(@PathVariable Long id, RedirectAttributes attributes) {
		try {
			service.delete(id);
			// Mensagem de sucesso (opcional)
			attributes.addFlashAttribute("mensagemSucesso", "Leitor excluído com sucesso!");
		}
		catch(DatabaseException e) {
			// Mensagem de erro capturando o que você escreveu no Service
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		
		return "redirect:/biblioteca/leitores";
	}
	
	
	//METODOS PARA ATUALIZAR UM LEITOR
	//Rota para carregar o formulário PREENCHIDO com os dados do leitor
	@GetMapping("/leitores/editar/{id}")
	public String editarLeitor(@PathVariable Long id, Model model) {
		// Busca o leitor no banco (retorna entidade Leitor)
		Leitor leitor = service.findById(id);
		
		// Converte para DTO (pois o formulário espera um DTO)
		LeitorDTO dto = new LeitorDTO(leitor);
		
		// Manda o DTO preenchido para a tela
		model.addAttribute("leitorDTO", dto);
		
		// Reutilizamos o mesmo HTML!
		return "formulario_leitor";
	}
	
	//Rota para receber os dados alterados e salvar
	@PostMapping("/leitores/editar/{id}")
	public String atualizarLeitor(@PathVariable Long id, @ModelAttribute("leitorDTO") LeitorDTO dto) {
		// Chama o serviço de atualização
		service.update(id, dto);
		return "redirect:/biblioteca/leitores";
	}
}
































