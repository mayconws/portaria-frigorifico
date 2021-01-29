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

import com.frigorifico.mendes.model.Permissao;
import com.frigorifico.mendes.repository.Permissoes;
import com.frigorifico.mendes.service.CadastroPermissaoService;
import com.frigorifico.mendes.service.exception.NomePermissaoJaCadastradoException;

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {
	
	@Autowired
	private Permissoes permissoes;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Permissao permissao) {
		ModelAndView mv = new ModelAndView("permissao/CadastroPermissao");
		mv.addObject("conteudo", permissoes.findAll());
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView cadastrar(@Valid Permissao permissao, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return nova(permissao);
		}

		try {
			cadastroPermissaoService.salvar(permissao);
		} catch (NomePermissaoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(permissao);
		}

		attributes.addFlashAttribute("mensagem", "Permissão salva com sucesso!");
		return new ModelAndView("redirect:/permissoes/nova");

	}

}
