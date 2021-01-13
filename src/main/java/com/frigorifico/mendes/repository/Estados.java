package com.frigorifico.mendes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Long> {
	
	

}
