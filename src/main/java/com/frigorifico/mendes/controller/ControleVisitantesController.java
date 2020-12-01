package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controle/visitantes")
public class ControleVisitantesController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "controleVisitante/CadastroControleVisitante";
	}
}
