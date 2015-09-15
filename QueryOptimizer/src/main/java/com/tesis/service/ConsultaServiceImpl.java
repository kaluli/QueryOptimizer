package com.tesis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tesis.model.Consulta;
import com.tesis.repository.ConsultaRepository;

@Service("consultaService")
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Transactional
	public Consulta save(Consulta consulta) {
		consultaRepository.save(consulta);
		return null;
	}

}