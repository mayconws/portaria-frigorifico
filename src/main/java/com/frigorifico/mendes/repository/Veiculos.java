package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Veiculo;

@Repository
public interface Veiculos extends JpaRepository<Veiculo, Long> {
	
	public Optional<Veiculo> findByPlacaIgnoreCase(String placa);

}
