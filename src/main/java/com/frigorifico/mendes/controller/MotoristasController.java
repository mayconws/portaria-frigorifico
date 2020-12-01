package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/motoristas")
public class MotoristasController {
	
	@RequestMapping("/novo")
	public String nova() {
		return "motorista/CadastroMotorista";
	}

}
