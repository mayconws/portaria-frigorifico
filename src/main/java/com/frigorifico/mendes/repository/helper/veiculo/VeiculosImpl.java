package com.frigorifico.mendes.repository.helper.veiculo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.frigorifico.mendes.dto.VeiculoDTO;
import com.frigorifico.mendes.storage.FotoStorage;

public class VeiculosImpl implements VeiculosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Override
	public List<VeiculoDTO> porPlacaOuModelo(String placaOuModelo) {
		String jpql = "select new com.frigorifico.mendes.dto.VeiculoDTO(v.codigo, v.placa, v.modelo, v.transportadora, v.foto) "
				+ "from Veiculo v inner join v.modelo inner join v.transportadora e where lower(v.placa) like lower(:placaOuModelo)";
		List<VeiculoDTO> veiculosFiltrados = manager.createQuery(jpql, VeiculoDTO.class)
					.setParameter("placaOuModelo", placaOuModelo + "%")
					.getResultList();
		veiculosFiltrados.forEach(c -> c.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + c.getFoto())));
		return veiculosFiltrados;
	}

}
