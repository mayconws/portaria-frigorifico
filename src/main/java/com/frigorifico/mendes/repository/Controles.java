package com.frigorifico.mendes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Controle;
import com.frigorifico.mendes.repository.helper.controle.ControlesQueries;

@Repository
public interface Controles extends JpaRepository<Controle, Long>, ControlesQueries {
	
}
