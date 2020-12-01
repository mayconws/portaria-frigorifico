package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@RequestMapping("/nova")
	public String nova() {
		return "cidade/CadastroCidade";
	}

}
