package com.trabajofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajofinal.model.Consulta;

@Repository("consultaRepository")
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("select distinct avg(c.time) from Consulta c where upper(c.query) like upper(:query)")
	public Double getTimeAverage(@Param("query") String query);
		
}
