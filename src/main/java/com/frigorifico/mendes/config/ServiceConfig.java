package com.frigorifico.mendes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.frigorifico.mendes.service.CadastroVeiculoService;
import com.frigorifico.mendes.storage.FotoStorage;
import com.frigorifico.mendes.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroVeiculoService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}

}
