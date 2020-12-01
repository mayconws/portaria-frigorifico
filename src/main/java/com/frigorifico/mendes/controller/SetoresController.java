package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setores")
public class SetoresController {
	
	@RequestMapping("/novo")
	public String nova() {
		return "setor/CadastroSetor";
	}

}
