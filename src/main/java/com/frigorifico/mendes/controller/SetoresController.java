package com.frigorifico.mendes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frigorifico.mendes.model.Setor;

@Controller
@RequestMapping("/setores")
public class SetoresController {

	@RequestMapping("/novo")
	public ModelAndView novo(Setor setor) {
		ModelAndView mv = new ModelAndView("setor/CadastroSetor");
		return mv;
	}

}
