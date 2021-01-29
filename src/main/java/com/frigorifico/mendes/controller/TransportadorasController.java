package com.frigorifico.mendes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.controller.page.PageWrapper;
import com.frigorifico.mendes.model.TipoPessoa;
import com.frigorifico.mendes.model.Transportadora;
import com.frigorifico.mendes.repository.Estados;
import com.frigorifico.mendes.repository.Transportadoras;
import com.frigorifico.mendes.repository.filter.TransportadoraFilter;
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
	public ModelAndView novo(Transportadora transportadora, TransportadoraFilter transportadoraFilter, Pageable pageable) {
		ModelAndView mv = new ModelAndView("transportadora/CadastroTransportadora");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());	
		mv.addObject("conteudo", transportadoras.filtrar(transportadoraFilter, pageable));
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Transportadora transportadora, BindingResult result, RedirectAttributes attributes,
			TransportadoraFilter transportadoraFilter, Pageable pageable) {
		if (result.hasErrors()) {
			return novo(transportadora, transportadoraFilter, pageable);
		}
		
		try {
			cadastroTransportadoraService.salvar(transportadora);
		} catch (CpfCnpjTransportadoraJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(transportadora, transportadoraFilter, pageable);
		}
		
		attributes.addFlashAttribute("mensagem", "Transportadora salva com sucesso!");
		return new ModelAndView("redirect:/transportadoras/nova");
	}
	
//	@GetMapping
//	public ModelAndView pesquisar() {
//		ModelAndView mv = new ModelAndView("transportadora/PesquisaTransportadoras");
//		mv.addObject("tiposPessoa", TipoPessoa.values());
//		mv.addObject("estados", estados.findAll());
//		mv.addObject("transportadoras", transportadoras.findAll());
//		return mv;
//	}
	
	@GetMapping
	public ModelAndView pesquisar(TransportadoraFilter transportadoraFilter, BindingResult result
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("transportadora/PesquisaTransportadoras");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Transportadora> paginaWrapper = new PageWrapper<>(transportadoras.filtrar(transportadoraFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}
