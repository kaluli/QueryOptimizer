package com.tesis.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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
		super();
		this.query = query;
		this.iduser = iduser;
		this.idconfig = idconfig;
		this.created = created;
	}

	public String ejecutarConsulta(Consulta consulta){
		String results = null;
		
		return results;	
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

		
	
}
