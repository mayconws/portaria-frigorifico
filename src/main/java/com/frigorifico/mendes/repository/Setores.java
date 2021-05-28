package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Setor;

@Repository
public interface Setores extends JpaRepository<Setor, Long> {
	
	public Optional<Setor> findByNomeIgnoreCase(String nome);

}
