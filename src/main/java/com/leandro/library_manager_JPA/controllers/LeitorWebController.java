package com.leandro.library_manager_JPA.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
