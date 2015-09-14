package com.tesis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesis.model.Configuracion;
import com.tesis.repository.ConfiguracionRepository;



@Service("configuracionService")
public class ConfiguracionServiceImpl implements ConfiguracionService {

	@Autowired
	private ConfiguracionRepository configuracionRepository;

	public Configuracion findById(int id) {
		return configuracionRepository.findById(id);
	}

	@Transactional
	public Configuracion save(Configuracion configuracion) {
		configuracionRepository.save(configuracion);
		return null;
	}

	@Transactional
	public Configuracion delete(Configuracion configuracion) {
		configuracionRepository.delete(configuracion);
		return null;
	}

	public Configuracion findByName(String name) {
		return configuracionRepository.findByName(name);
	}
	
	

}