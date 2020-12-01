package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@RequestMapping("/novo")
	public String novo() {
		return "usuario/CadastroUsuario";
	}

}
