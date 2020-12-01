package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controle/veiculos")
public class ControleVeiculosController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "controleVeiculo/CadastroControleVeiculo";
	}
}
