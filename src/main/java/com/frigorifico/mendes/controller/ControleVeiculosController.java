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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.controller.validator.MovimentacaoValidator;
import com.frigorifico.mendes.dto.MovimentacaoMes;
import com.frigorifico.mendes.mail.Mailer;
import com.frigorifico.mendes.model.ItemVeiculo;
import com.frigorifico.mendes.model.Movimentacao;
import com.frigorifico.mendes.model.SituacaoVeiculo;
import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.repository.Motoristas;
import com.frigorifico.mendes.repository.Movimentacoes;
import com.frigorifico.mendes.repository.Veiculos;
import com.frigorifico.mendes.security.UsuarioSistema;
import com.frigorifico.mendes.service.CadastroControleVeiculoService;
import com.frigorifico.mendes.session.TabelasItensSession;

@Controller
@RequestMapping("/controle/veiculos")
public class ControleVeiculosController {
	
	@Autowired
	private Veiculos veiculos;
	
	@Autowired
	private Motoristas motoristas;
	
	@Autowired
	private Movimentacoes movimentacoes;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private CadastroControleVeiculoService cadastroControleVeiculoService;
	
	@Autowired
	private MovimentacaoValidator movimentacaoValidator;
	
	@Autowired
	private Mailer mailer;
	
	@InitBinder("movimentacao")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(movimentacaoValidator);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Movimentacao movimentacao) {
		ModelAndView mv = new ModelAndView("controleVeiculo/CadastroControleVeiculo");
		mv.addObject("situacoes", SituacaoVeiculo.values());
		mv.addObject("motoristas", motoristas.findAll());	
		mv.addObject("veiculos", veiculos.findAll());
		mv.addObject("conteudo", movimentacoes.findAll());
		mv.addObject("veiculosTotal", movimentacoes.veiculosTotal());
		
		setUuid(movimentacao);
		
		mv.addObject("itens", movimentacao.getItens());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(Movimentacao movimentacao, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarMovimentacao(movimentacao, result);		
		if(result.hasErrors()) {
			return novo(movimentacao);
		}
		
		movimentacao.setUsuario(usuarioSistema.getUsuario());		
		
		cadastroControleVeiculoService.salvar(movimentacao);
		attributes.addFlashAttribute("mensagem", "Veiculo salvo com sucesso");
		return new ModelAndView("redirect:/controle/veiculos/novo");
	}	
	
	@PostMapping(value = "/novo", params = "lancar")
	public ModelAndView lancar(Movimentacao movimentacao, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarMovimentacao(movimentacao, result);
		if(result.hasErrors()) {
			return novo(movimentacao);
		}
		
		movimentacao.setUsuario(usuarioSistema.getUsuario());		
		
		cadastroControleVeiculoService.lancar(movimentacao);
		attributes.addFlashAttribute("mensagem", "Movimentação lançada com sucesso");
		return new ModelAndView("redirect:/controle/veiculos/novo");
	}
	
	@PostMapping(value = "/novo", params = "finalizar")
	public ModelAndView finalizar(Movimentacao movimentacao, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarMovimentacao(movimentacao, result);
		if(result.hasErrors()) {
			return novo(movimentacao);
		}
		
		movimentacao.setUsuario(usuarioSistema.getUsuario());		
		
		cadastroControleVeiculoService.finalizar(movimentacao);
		attributes.addFlashAttribute("mensagem", "Movimentação finalizada com sucesso");
		return new ModelAndView("redirect:/controle/veiculos/novo");
	}
	
	@PostMapping(value = "/novo", params = "enviarEmail")
	public ModelAndView enviarEmail(Movimentacao movimentacao, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarMovimentacao(movimentacao, result);
		if(result.hasErrors()) {
			return novo(movimentacao);
		}
		
		movimentacao.setUsuario(usuarioSistema.getUsuario());		
		
		cadastroControleVeiculoService.salvar(movimentacao);
		mailer.enviar(movimentacao);
		
		attributes.addFlashAttribute("mensagem", "Movimentação enviada por e-mail com sucesso");
		return new ModelAndView("redirect:/controle/veiculos/novo");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarVeiculo(Long codigoVeiculo, String uuid) {
		Veiculo veiculo = veiculos.findOne(codigoVeiculo);
		tabelaItens.adicionarVeiculo(uuid, veiculo, 1);
		return mvTabelaItensVeiculo(uuid);
		
	}
	
	@PutMapping("/item/{codigoVeiculo}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoVeiculo") Veiculo veiculo, 
			Integer quantidade, String uuid) {
		tabelaItens.alterarQuantidadeItens(uuid, veiculo, quantidade);
		return mvTabelaItensVeiculo(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoVeiculo}")
	public ModelAndView excluirItem(@PathVariable("codigoVeiculo") Veiculo veiculo, 
			@PathVariable String uuid) {
		tabelaItens.excluirItem(uuid, veiculo);
		return mvTabelaItensVeiculo(uuid);
	}
	
	@GetMapping("/novo/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Movimentacao movimentacao = movimentacoes.buscarVeiculos(codigo);
		
		setUuid(movimentacao);
		for (ItemVeiculo item : movimentacao.getItens()) {
			tabelaItens.adicionarVeiculo(movimentacao.getUuid(), item.getVeiculo(), item.getQuantidadeVeiculo());
		}
		
		ModelAndView mv = novo(movimentacao);
		mv.addObject(movimentacao);
		return mv;
	}
	
	@PostMapping(value = "/novo", params = "cancelar")
	public ModelAndView cancelar(Movimentacao movimentacao, BindingResult result
				, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		try {
			cadastroControleVeiculoService.cancelar(movimentacao);
		} catch (AccessDeniedException e) {
			return new ModelAndView("/403");
		}
		
		attributes.addFlashAttribute("mensagem", "Movimentação cancelada com sucesso");
		return new ModelAndView("redirect:/controle/veiculos/novo/" + movimentacao.getCodigo());
	}
	
	@GetMapping("/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroControleVeiculoService.excluir(codigo);

		attributes.addFlashAttribute("mensagem", "Movimentação excluída com sucesso!");
		return "redirect:/controle/veiculos/novo";
	}
	
	@GetMapping("/totalPorMes")
	public @ResponseBody List<MovimentacaoMes> listarTotalMovimentacaoPorMes() {
		return movimentacoes.totalPorMes();
	}
	
//	@GetMapping
//	public ModelAndView pesquisar() {
//		ModelAndView mv = new ModelAndView("controleVeiculo/CadastroControleVeiculo");
//		mv.addObject("situacoes", SituacaoVeiculo.values());
//		mv.addObject("motoristas", motoristas.findAll());	
//		mv.addObject("veiculos", veiculos.findAll());
//		mv.addObject("conteudo", movimentacoes.findAll());
//		return mv;
//	}

	private ModelAndView mvTabelaItensVeiculo(String uuid) {
		ModelAndView mv = new ModelAndView("controleVeiculo/TabelaItensVeiculo");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("quantidadeTotal", tabelaItens.getQuantidadeTotal(uuid));
		return mv;
	}
	
	private void validarMovimentacao(Movimentacao movimentacao, BindingResult result) {
		movimentacao.adicionarItens(tabelaItens.getItens(movimentacao.getUuid()));
		
		movimentacaoValidator.validate(movimentacao, result);
	}
	
	private void setUuid(Movimentacao movimentacao) {
		if (StringUtils.isEmpty(movimentacao.getUuid())) {
			movimentacao.setUuid(UUID.randomUUID().toString());
		}
	}
}
