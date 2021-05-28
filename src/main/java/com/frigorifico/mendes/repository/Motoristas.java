package com.frigorifico.mendes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Motorista;

@Repository
public interface Motoristas extends JpaRepository<Motorista, Long> {
	
	public Optional<Motorista> findByCpfIgnoreCase(String cpf);

	public List<Motorista> findByNomeStartingWithIgnoreCase(String nome);
	
}
