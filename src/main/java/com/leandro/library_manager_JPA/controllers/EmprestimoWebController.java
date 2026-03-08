package com.leandro.library_manager_JPA.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leandro.library_manager_JPA.entities.Emprestimo;
import com.leandro.library_manager_JPA.services.EmprestimoService;

@Controller
@RequestMapping("/biblioteca")
public class EmprestimoWebController {

	
	@Autowired
	private EmprestimoService service;
	
	@GetMapping("/emprestimos")
	public String listaEmprestimo(Model model) {
		
		List<Emprestimo> list = service.findAll();
		
		model.addAttribute("meusEmprestimos", list);
		
		return "emprestimos";
	}
}
