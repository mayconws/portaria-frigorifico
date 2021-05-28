package com.frigorifico.mendes.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.controller.validator.ControleValidator;
import com.frigorifico.mendes.dto.MovimentacaoMes;
import com.frigorifico.mendes.model.Controle;
import com.frigorifico.mendes.repository.Controles;
import com.frigorifico.mendes.repository.Setores;
import com.frigorifico.mendes.repository.Visitantes;
import com.frigorifico.mendes.security.UsuarioSistema;
import com.frigorifico.mendes.service.CadastroControleVisitanteService;

@Controller
@RequestMapping("/controle/visitantes")
public class ControleVisitantesController {
	
	@Autowired
	private Visitantes visitantes;
	
	@Autowired
	private Setores setores;
	
	@Autowired
	private Controles controles;
	
	@Autowired
	private CadastroControleVisitanteService cadastroControleVisitanteService;
	
	@Autowired
	private ControleValidator controleValidator;
	
	@InitBinder("controle")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(controleValidator);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Controle controle) {
		ModelAndView mv = new ModelAndView("controleVisitante/CadastroControleVisitante");
		mv.addObject("visitantes", visitantes.findAll());	
		mv.addObject("setores", setores.findAll());
		mv.addObject("conteudo", controles.findAll());
		mv.addObject("visitantesTotal", controles.visitantesTotal());
		
		setUuid(controle);
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(Controle controle, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarControle(controle, result);		
		if(result.hasErrors()) {
			return novo(controle);
		}
		
		controle.setUsuario(usuarioSistema.getUsuario());		
		
		cadastroControleVisitanteService.salvar(controle);
		attributes.addFlashAttribute("mensagem", "Visitante salvo com sucesso");
		return new ModelAndView("redirect:/controle/visitantes/novo");
	}	
	
	@PostMapping(value = "/novo", params = "lancar")
	public ModelAndView lancar(Controle controle, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarControle(controle, result);
		if(result.hasErrors()) {
			return novo(controle);
		}
		
		controle.setUsuario(usuarioSistema.getUsuario());		
		
		cadastroControleVisitanteService.lancar(controle);
		attributes.addFlashAttribute("mensagem", "Controle lançado com sucesso");
		return new ModelAndView("redirect:/controle/visitantes/novo");
	}
	
	@PostMapping(value = "/novo", params = "finalizar")
	public ModelAndView finalizar(Controle controle, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarControle(controle, result);
		if(result.hasErrors()) {
			return novo(controle);
		}
		
		controle.setUsuario(usuarioSistema.getUsuario());		
		
		cadastroControleVisitanteService.finalizar(controle);
		attributes.addFlashAttribute("mensagem", "Controle finalizado com sucesso");
		return new ModelAndView("redirect:/controle/visitantes/novo");
	}
	
	@GetMapping("/novo/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Controle controle) {
		
		ModelAndView mv = novo(controle);
		mv.addObject(controle);
		return mv;
	}
	
	@PostMapping(value = "/novo", params = "cancelar")
	public ModelAndView cancelar(Controle controle, BindingResult result
				, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		try {
			cadastroControleVisitanteService.cancelar(controle);
		} catch (AccessDeniedException e) {
			return new ModelAndView("/403");
		}
		
		attributes.addFlashAttribute("mensagem", "Controle cancelado com sucesso");
		return new ModelAndView("redirect:/controle/visitantes/novo/" + controle.getCodigo());
	}
	
	@GetMapping("/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroControleVisitanteService.excluir(codigo);

		attributes.addFlashAttribute("mensagem", "Controle excluído com sucesso!");
		return "redirect:/controle/visitantes/novo";
	}
	
	@GetMapping("/totalPorMes")
	public @ResponseBody List<MovimentacaoMes> listarTotalMovimentacaoPorMes() {
		return controles.totalPorMes();
	}
	
	private void validarControle(Controle controle, BindingResult result) {
		
		controleValidator.validate(controle, result);
	}
	
	private void setUuid(Controle controle) {
		if (StringUtils.isEmpty(controle.getUuid())) {
			controle.setUuid(UUID.randomUUID().toString());
		}
	}
}
