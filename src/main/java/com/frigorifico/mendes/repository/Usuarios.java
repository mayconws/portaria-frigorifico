package com.frigorifico.mendes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);

}
