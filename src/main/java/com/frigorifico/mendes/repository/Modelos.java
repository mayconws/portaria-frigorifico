package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Modelo;

@Repository
public interface Modelos extends JpaRepository<Modelo, Long> {
	
	public Optional<Modelo> findByNomeIgnoreCase(String nome);

}
