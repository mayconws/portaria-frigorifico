package com.frigorifico.mendes.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.frigorifico.mendes.service.exception.NomeModeloJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NomeModeloJaCadastradoException.class)
	public ResponseEntity<String> handleNomeModeloJaCadastradoException(NomeModeloJaCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
