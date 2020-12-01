package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transportadoras")
public class TransportadorasController {
	
	@RequestMapping("/nova")
	public String nova() {
		return "transportadora/CadastroTransportadora";
	}

}
