package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grupos")
public class GruposController {
	
	@RequestMapping("/novo")
	public String nova() {
		return "grupo/CadastroGrupo";
	}

}
