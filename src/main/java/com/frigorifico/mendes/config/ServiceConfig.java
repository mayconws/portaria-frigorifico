package com.frigorifico.mendes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.frigorifico.mendes.service.CadastroVeiculoService;
import com.frigorifico.mendes.storage.FotoStorage;

@Configuration
@ComponentScan(basePackageClasses = { CadastroVeiculoService.class, FotoStorage.class } )
public class ServiceConfig {
	

}
