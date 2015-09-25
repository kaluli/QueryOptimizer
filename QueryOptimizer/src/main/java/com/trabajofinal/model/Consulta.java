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
	
	private void limpiarCache(JdbcTemplate jt){
		jt.execute("RESET QUERY CACHE;");
		jt.execute("FLUSH QUERY CACHE;");
		jt.execute("SET SESSION query_cache_type=0");
		jt.execute("SET @@profiling = 0");
		jt.execute("SET @@profiling_history_size = 0");
		jt.execute("SET @@profiling_history_size = 100"); 
	}
	
	private Double calcularTiempo(JdbcTemplate jt, Consulta consulta) {
		jt.execute("SET @@profiling = 1");
		jt.execute(consulta.getQuery());
		return jt.queryForObject("SELECT SUM(DURATION) FROM INFORMATION_SCHEMA.PROFILING WHERE QUERY_ID=1", Double.class);		
	}
	
		
	private List<Map<String,Object>> ejecutarQuery(JdbcTemplate jt, Consulta consulta){
		return jt.queryForList(consulta.getQuery());			
	}
	
	public List<Map<String, Object>> gestionarConsulta(Consulta consulta, String db){
		SingleConnectionDataSource ds = consulta.conectarBD(db);
		JdbcTemplate jt = new JdbcTemplate(ds);
		this.limpiarCache(jt);
		List<Map<String,Object>> resultados = consulta.ejecutarQuery(jt, consulta);
		consulta.setTime(this.calcularTiempo(jt,consulta));
		return resultados;	
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
