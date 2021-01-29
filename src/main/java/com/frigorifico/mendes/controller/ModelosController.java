package com.frigorifico.mendes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.model.Modelo;
import com.frigorifico.mendes.repository.Modelos;
import com.frigorifico.mendes.service.CadastroModeloService;
import com.frigorifico.mendes.service.exception.NomeModeloJaCadastradoException;

@Controller
@RequestMapping("/modelos")
public class ModelosController {

	@Autowired
	private CadastroModeloService cadastroModeloService;
	
	@Autowired
	private Modelos modelos;

	@RequestMapping("/novo")
	public ModelAndView novo(Modelo modelo) {
		ModelAndView mv = new ModelAndView("modelo/CadastroModelo");
		mv.addObject("conteudo", modelos.findAll());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Modelo modelo, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(modelo);
		}

		try {
			cadastroModeloService.salvar(modelo);
		} catch (NomeModeloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(modelo);
		}

		attributes.addFlashAttribute("mensagem", "Modelo salvo com sucesso!");
		return new ModelAndView("redirect:/modelos/novo");

	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Modelo modelo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}

		modelo = cadastroModeloService.salvar(modelo);
		return ResponseEntity.ok(modelo);
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("modelo/PesquisaModelos");		
		mv.addObject("modelos", modelos.findAll());
		return mv;
	}

}
