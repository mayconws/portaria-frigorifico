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

import com.frigorifico.mendes.model.Motorista;
import com.frigorifico.mendes.repository.Motoristas;
import com.frigorifico.mendes.repository.Transportadoras;
import com.frigorifico.mendes.service.CadastroMotoristaService;
import com.frigorifico.mendes.service.exception.MotoristaJaCadastradoException;

@Controller
@RequestMapping("/motoristas")
public class MotoristasController {
	
	@Autowired
	private Motoristas motoristas;
	
	@Autowired
	private Transportadoras transportadoras;
	
	@Autowired
	private CadastroMotoristaService cadastroMotoristaService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Motorista motorista) {
		ModelAndView mv = new ModelAndView("motorista/CadastroMotorista");
		mv.addObject("transportadoras", transportadoras.findAll());	
		mv.addObject("conteudo", motoristas.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Motorista motorista, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(motorista);
		}

		try {
			cadastroMotoristaService.salvar(motorista);
		} catch (MotoristaJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(motorista);
		}

		attributes.addFlashAttribute("mensagem", "Motorista salvo com sucesso!");
		return new ModelAndView("redirect:/motoristas/novo");

	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("motorista/PesquisaMotoristas");		
		mv.addObject("transportadoras", transportadoras.findAll());	
		mv.addObject("motoristas", motoristas.findAll());
		return mv;
	}

}
