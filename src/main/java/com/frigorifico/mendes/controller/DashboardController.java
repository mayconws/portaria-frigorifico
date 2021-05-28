package com.frigorifico.mendes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frigorifico.mendes.repository.Controles;
import com.frigorifico.mendes.repository.Movimentacoes;

@Controller
public class DashboardController {
	
	@Autowired
	private Movimentacoes movimentacoes;
	
	@Autowired
	private Controles controles;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		mv.addObject("veiculosNoAno", movimentacoes.totalVeiculosNoano());
		mv.addObject("veiculosNoMes", movimentacoes.totalVeiculosNoMes());
		mv.addObject("visitantesNoAno", controles.totalVisitantesNoano());
		mv.addObject("visitantesNoMes", controles.totalVisitantesNoMes());
		
		return mv;
	}

}
