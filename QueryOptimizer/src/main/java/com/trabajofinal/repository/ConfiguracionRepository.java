package com.trabajofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajofinal.model.Configuracion;

@Repository("configuracionRepository")
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Long> {

	@Query("select s from Configuracion s where s.id = :id")
	public Configuracion findById(@Param("id") int id);

	@Query("select s from Configuracion s where s.name = :name")
	public Configuracion findByName(@Param("name") String name);

	
	
}