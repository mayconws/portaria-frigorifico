package com.frigorifico.mendes.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.frigorifico.mendes.MendesApplication;
import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.storage.FotoStorage;

public class VeiculoEntityListener {
	
	@PostLoad
	public void postLoad(final Veiculo veiculo) {
		FotoStorage fotoStorage = MendesApplication.getBean(FotoStorage.class);
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		veiculo.setUrlFoto(fotoStorage.getUrl(veiculo.getFotoOuMock()));
		veiculo.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + veiculo.getFotoOuMock()));
	}

}
