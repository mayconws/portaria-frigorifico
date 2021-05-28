package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Visitante;

@Repository
public interface Visitantes extends JpaRepository<Visitante, Long> {
	
	public Optional<Visitante> findByNomeIgnoreCase(String nome);
	
	public Optional<Visitante> findByCpfIgnoreCase(String cpf);

}
