package com.frigorifico.mendes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}
