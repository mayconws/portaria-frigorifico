package com.frigorifico.mendes.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.config.Veiculo;

@Controller
public class VeiculosController {

	@RequestMapping("/veiculos/novo")
	public String novo(Veiculo veiculo) {
		return "veiculo/CadastroVeiculo";
	}

	@RequestMapping(value = "/veiculos/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Veiculo veiculo, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(veiculo);
		}

		// Salvar no banco de dados...
		attributes.addFlashAttribute("mensagem", "Veículo salvo com sucesso!");
		System.out.println(">>> PLaca: " + veiculo.getPlaca());
		return "redirect:/veiculos/novo";

	}

}
