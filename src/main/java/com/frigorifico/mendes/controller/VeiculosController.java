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

import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.repository.Modelos;
import com.frigorifico.mendes.repository.Transportadoras;
import com.frigorifico.mendes.repository.Veiculos;
import com.frigorifico.mendes.service.CadastroVeiculoService;
import com.frigorifico.mendes.service.exception.VeiculoJaCadastradoException;

@Controller
@RequestMapping("/veiculos")
public class VeiculosController {
	
	@Autowired
	private Modelos modelos;
	
	@Autowired
	private Veiculos veiculos;
	
	@Autowired
	private Transportadoras transportadoras;
	
	@Autowired
	private CadastroVeiculoService cadastroVeiculoService;

	@RequestMapping("/novo")
	public ModelAndView novo(Veiculo veiculo) {
		ModelAndView mv = new ModelAndView("veiculo/CadastroVeiculo");
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("transportadoras", transportadoras.findAll());
		mv.addObject("conteudo", veiculos.findAll());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Veiculo veiculo, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(veiculo);
		}
		
		try {
			cadastroVeiculoService.salvar(veiculo);
		} catch (VeiculoJaCadastradoException e) {
			result.rejectValue("placa", e.getMessage(), e.getMessage());
			return novo(veiculo);
		}

		attributes.addFlashAttribute("mensagem", "Veículo salvo com sucesso!");
		return new ModelAndView("redirect:/veiculos/novo");

	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("veiculo/PesquisaVeiculos");
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("transportadoras", transportadoras.findAll());	
		mv.addObject("veiculos", veiculos.findAll());
		return mv;
	}

}
