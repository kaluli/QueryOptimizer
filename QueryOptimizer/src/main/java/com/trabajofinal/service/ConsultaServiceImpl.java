package com.trabajofinal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajofinal.model.Consulta;
import com.trabajofinal.repository.ConsultaRepository;

@Service("consultaService")
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Transactional
	public Consulta save(Consulta consulta) {
		consultaRepository.save(consulta);
		return null;
	}

	@Transactional
	public Double getTimeAverage(String query) {
		return consultaRepository.getTimeAverage(query);		
	}

}