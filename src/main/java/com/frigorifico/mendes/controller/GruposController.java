package com.frigorifico.mendes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.model.Grupo;
import com.frigorifico.mendes.repository.Grupos;
import com.frigorifico.mendes.service.CadastroGrupoService;
import com.frigorifico.mendes.service.exception.NomeGrupoJaCadastradoException;

@Controller
@RequestMapping("/grupos")
public class GruposController {
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = new ModelAndView("grupo/CadastroGrupo");
		mv.addObject("conteudo", grupos.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Grupo grupo, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(grupo);
		}

		try {
			cadastroGrupoService.salvar(grupo);
		} catch (NomeGrupoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(grupo);
		}

		attributes.addFlashAttribute("mensagem", "Grupo salvo com sucesso!");
		return new ModelAndView("redirect:/grupos/novo");

	}

}
