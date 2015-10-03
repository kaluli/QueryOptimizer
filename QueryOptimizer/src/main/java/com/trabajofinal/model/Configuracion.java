package com.trabajofinal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="configuraciones")
public class Configuracion {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	private int iduser;
	
	private String name;
	private String file;
	private String engine;
		
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date created;

	public Configuracion(){
	}
	
	public Configuracion(int iduser, String name, String file, Date created) {
		super();
		this.iduser = iduser;
		this.name = name;
		this.file = file;
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngine() {
		return engine;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@OneToMany(targetEntity=User.class, mappedBy="id", fetch=FetchType.EAGER)
	private List<User> usuarios;
	
	
}