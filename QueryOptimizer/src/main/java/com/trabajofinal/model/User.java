package com.trabajofinal.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty
	@Size(min=4, max=20)
	private String username;		
	
	@NotEmpty
	@Size(min=4, max=8)
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}*/

	/*@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval =
	        true)
	@JoinColumn(name = "id", referencedColumnName = "iduser")
	private UserProfile userProfile;
	*/
	
	@OneToMany(targetEntity=Configuracion.class, mappedBy="iduser", fetch=FetchType.EAGER)
	@OrderBy("created DESC") 
	private List<Configuracion> configuraciones;   
	
	@OneToMany(targetEntity=Consulta.class, mappedBy="iduser", fetch=FetchType.EAGER)
	@OrderBy("created DESC") 
	private List<Consulta> consultas;   
		
	public List<Configuracion> getConfiguraciones() {
		return this.configuraciones;
	} 
	public void setConfiguraciones(List<Configuracion> configuraciones) {
		this.configuraciones = configuraciones;
	}
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	
}
