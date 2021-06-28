package com.frigorifico.mendes.controller;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frigorifico.mendes.dto.PeriodoRelatorio;
import com.frigorifico.mendes.service.RelatorioService;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

	@Autowired
	private RelatorioService relatorioService;

	@GetMapping("/controleVeiculos")
	public ModelAndView relatorioVeiculosFinalizados() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioControleVeiculos");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}

	@PostMapping("/controleVeiculos")
	public ResponseEntity<byte[]> gerarRelatorioVeiculosFinalizados(PeriodoRelatorio periodoRelatorio) throws Exception {
		byte[] relatorio = relatorioService.gerarRelatorioControleVeiculos(periodoRelatorio);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}

}
