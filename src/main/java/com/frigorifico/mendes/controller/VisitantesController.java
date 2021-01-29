package com.frigorifico.mendes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.model.Visitante;
import com.frigorifico.mendes.repository.Visitantes;
import com.frigorifico.mendes.service.CadastroVisitanteService;
import com.frigorifico.mendes.service.exception.VisitanteJaCadastradoException;

@Controller
@RequestMapping("/visitantes")
public class VisitantesController {
	
	@Autowired
	private Visitantes visitantes;
	
	@Autowired
	private CadastroVisitanteService cadastroVisitanteService;

	@RequestMapping("/novo")
	public ModelAndView novo(Visitante visitante) {
		ModelAndView mv = new ModelAndView("visitante/CadastroVisitante");	
		mv.addObject("conteudo", visitantes.findAll());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Visitante visitante, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(visitante);
		}
		
		try {
			cadastroVisitanteService.salvar(visitante);
		} catch (VisitanteJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(visitante);
		}

		attributes.addFlashAttribute("mensagem", "Visitante salvo com sucesso!");
		return new ModelAndView("redirect:/visitantes/novo");

	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("visitante/PesquisaVisitantes");		
		mv.addObject("visitantes", visitantes.findAll());
		return mv;
	}

}
