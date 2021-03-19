package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.repository.helper.veiculo.VeiculosQueries;

@Repository
public interface Veiculos extends JpaRepository<Veiculo, Long>, VeiculosQueries {
	
	public Optional<Veiculo> findByPlacaIgnoreCase(String placa);
	
//	@Query("select new com.frigorifico.mendes.dto.VeiculoDTO(codigo, placa, foto) "
//			+ "from Veiculo where lower(placa) like lower(:placaOuModelo)")
//	public List<VeiculoDTO> porPlacaOuModelo(@Param("placaOuModelo") String placaOuModelo);

}
