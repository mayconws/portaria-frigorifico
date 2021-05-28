package com.frigorifico.mendes.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frigorifico.mendes.dto.PeriodoRelatorio;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@GetMapping("/controleVeiculos")
	public ModelAndView relatorioControleVeiculos() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioControleVeiculos");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@PostMapping("/controleVeiculos")
	public ModelAndView gerarRelatorioControleVeiculos(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		return new ModelAndView("relatorio_movimentacao_veiculos", parametros);
	}
	
	@GetMapping("/controleVisitantes")
	public ModelAndView relatorioControleVisitantes() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioControleVisitantes");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@PostMapping("/controleVisitantes")
	public ModelAndView gerarRelatorioControleVisitantes(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		return new ModelAndView("relatorio_movimentacao_visitantes", parametros);
	}
	
	@GetMapping("/veiculos")
	public ModelAndView gerarRelatorioVeiculos() {
		Map<String, Object> parametros = new HashMap<>();	
		
		parametros.put("format", "pdf");		
		
		return new ModelAndView("relatorio_veiculos", parametros);
	}
	
	@GetMapping("/visitantes")
	public ModelAndView gerarRelatorioVisitantes() {
		Map<String, Object> parametros = new HashMap<>();	
		
		parametros.put("format", "pdf");		
		
		return new ModelAndView("relatorio_visitante", parametros);
	}

}
