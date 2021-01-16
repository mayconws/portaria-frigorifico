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

import com.frigorifico.mendes.model.Setor;
import com.frigorifico.mendes.repository.Setores;
import com.frigorifico.mendes.service.CadastroSetorService;
import com.frigorifico.mendes.service.exception.VeiculoJaCadastradoException;

@Controller
@RequestMapping("/setores")
public class SetoresController {
	
	@Autowired
	private Setores setores;
	
	@Autowired
	private CadastroSetorService cadastroSetorService;

	@RequestMapping("/novo")
	public ModelAndView novo(Setor setor) {
		ModelAndView mv = new ModelAndView("setor/CadastroSetor");
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Setor setor, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(setor);
		}
		
		try {
			cadastroSetorService.salvar(setor);
		} catch (VeiculoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(setor);
		}

		attributes.addFlashAttribute("mensagem", "Setor salvo com sucesso!");
		return new ModelAndView("redirect:/setores/novo");

	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("setor/PesquisaSetores");
		mv.addObject("setores", setores.findAll());	
		return mv;
	}

}
