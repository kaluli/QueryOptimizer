package com.trabajofinal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.jdbc.core.JdbcTemplate;

import com.trabajofinal.utils.Database;

@Entity
@Table(name="consultas")
public class Consulta {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "query", columnDefinition = "text")
	@NotEmpty
	private String query;		
	
	@NotNull
	private int iduser;

	@NotNull
	private int idconfig;
	
	private Double time;
	
	private Date created;

	public Consulta(){		
	}
	
	public Consulta(String query, int iduser, int idconfig, Date created) {
		this.query = query;
		this.iduser = iduser;
		this.idconfig = idconfig;
		this.created = created;
	}

			
	public List<Map<String, Object>> gestionarConsulta(String db, Database database){
		// conectar a la bd seleccionada
		JdbcTemplate jt = database.conectarBD(db);		
		List<Map<String, Object>> result = database.ejecutarQuery(jt, this, database);
		database.desconectarBD();
		return result;	
	}	

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getIdconfig() {
		return idconfig;
	}

	public void setIdconfig(int idconfig) {
		this.idconfig = idconfig;
	}

	@OneToMany(targetEntity=Configuracion.class, mappedBy="id", fetch=FetchType.EAGER)
	private List<Configuracion> configuracion;

	public List<Configuracion> getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(List<Configuracion> configuracion) {
		this.configuracion = configuracion;
	}
	
}