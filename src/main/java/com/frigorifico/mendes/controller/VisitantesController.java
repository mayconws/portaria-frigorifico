package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitantes")
public class VisitantesController {
	
	@RequestMapping("/novo")
	public String nova() {
		return "visitante/CadastroVisitante";
	}

}
