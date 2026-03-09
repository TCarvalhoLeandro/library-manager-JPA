package com.leandro.library_manager_JPA.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leandro.library_manager_JPA.dtos.LeitorDTO;
import com.leandro.library_manager_JPA.entities.Leitor;
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
	
}
