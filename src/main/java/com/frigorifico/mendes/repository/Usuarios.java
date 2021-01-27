package com.frigorifico.mendes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Usuario;
import com.frigorifico.mendes.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	
	public Optional<Usuario> findByEmail(String email);
	
	public List<Usuario> findByCodigoIn(Long[] codigos);
	
	@Query("from Usuario u where lower(u.email) = lower(?1) and u.ativo = true")
	public Optional<Usuario> porEmailEAtivo(String email);
	
	 @Query(value="select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario" )
	 public List<String> permissoes(@Param("usuario") Usuario usuario);

}
