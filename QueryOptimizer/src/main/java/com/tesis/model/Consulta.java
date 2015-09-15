package com.tesis.model;

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
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@Entity
@Table(name="consulta")
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
	
	private Date created;

	public Consulta(){		
	}
	
	public Consulta(String query, int iduser, int idconfig, Date created) {
		this.query = query;
		this.iduser = iduser;
		this.idconfig = idconfig;
		this.created = created;
	}

	private SingleConnectionDataSource conectarBD(String database){
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		  // Sería óptimo que leyera estos datos de un archivo 
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://127.0.0.1:3306/" + database;	  
	    ds.setUrl(url);
	    ds.setUsername("root");
	    ds.setPassword("kaluli32");
	    return ds;
   	}
	
	private List<Map<String,Object>> ejecutarQuery(SingleConnectionDataSource ds, Consulta consulta){
		JdbcTemplate jt = new JdbcTemplate(ds);	  
		return jt.queryForList(consulta.getQuery()); 	            			
	}
	
	public List<Map<String, Object>> gestionarConsulta(Consulta consulta, String db){
		SingleConnectionDataSource ds = consulta.conectarBD(db);
	    return consulta.ejecutarQuery(ds, consulta);	
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