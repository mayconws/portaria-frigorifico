package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Transportadora;

@Repository
public interface Transportadoras extends JpaRepository<Transportadora, Long> {
	
	public Optional<Transportadora> findByCpfOuCnpj(String cpfOuCnpj);

}
