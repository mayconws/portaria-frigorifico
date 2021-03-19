package com.frigorifico.mendes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.dto.VeiculoDTO;
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

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Veiculo veiculo, BindingResult result, Model model,
			RedirectAttributes attributes) {

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

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<VeiculoDTO> pesquisar(String placaOuModelo) {
		return veiculos.porPlacaOuModelo(placaOuModelo);
	}

	@GetMapping("/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroVeiculoService.excluir(codigo);

		attributes.addFlashAttribute("mensagem", "Veículo excluído com sucesso!");
		return "redirect:/veiculos/novo";
	}

//	@DeleteMapping("/novo/{codigo}")
//	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Veiculo veiculo) {
//		try {
//			cadastroVeiculoService.excluir(veiculo);
//		} catch (ImpossivelExcluirEntidadeException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//		return ResponseEntity.ok().build();
//	}

	@GetMapping("/novo/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Veiculo veiculo) {
		ModelAndView mv = novo(veiculo);
		mv.addObject(veiculo);
		return mv;
	}

}
