package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Permissao;

@Repository
public interface Permissoes extends JpaRepository<Permissao, Long> {
	
	public Optional<Permissao> findByNomeIgnoreCase(String nome);

}
