package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {
	
	@RequestMapping("/nova")
	public String nova() {
		return "permissao/CadastroPermissao";
	}

}
