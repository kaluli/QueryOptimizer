package com.tesis.service;

import com.tesis.model.Configuracion;

public interface ConfiguracionService {	
	public Configuracion findById(int id);
	public Configuracion findByName(String name);
	public Configuracion save(Configuracion configuracion);
	public Configuracion delete(Configuracion configuracion);
}