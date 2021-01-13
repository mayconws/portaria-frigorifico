package com.frigorifico.mendes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.model.TipoPessoa;
import com.frigorifico.mendes.model.Transportadora;
import com.frigorifico.mendes.repository.Estados;
import com.frigorifico.mendes.repository.Transportadoras;
import com.frigorifico.mendes.service.CadastroTransportadoraService;
import com.frigorifico.mendes.service.exception.CpfCnpjTransportadoraJaCadastradoException;

@Controller
@RequestMapping("/transportadoras")
public class TransportadorasController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Transportadoras transportadoras;
	
	@Autowired
	private CadastroTransportadoraService cadastroTransportadoraService;
	
	@RequestMapping("/nova")
	public ModelAndView novo(Transportadora transportadora) {
		ModelAndView mv = new ModelAndView("transportadora/CadastroTransportadora");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Transportadora transportadora, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(transportadora);
		}
		
		try {
			cadastroTransportadoraService.salvar(transportadora);
		} catch (CpfCnpjTransportadoraJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(transportadora);
		}
		
		attributes.addFlashAttribute("mensagem", "Transportadora salva com sucesso!");
		return new ModelAndView("redirect:/transportadoras/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("transportadora/PesquisaTransportadoras");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
		mv.addObject("transportadoras", transportadoras.findAll());
		return mv;
	}

}
