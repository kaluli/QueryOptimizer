package com.trabajofinal.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;


import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "query", columnDefinition = "text")
	@NotEmpty
	private String query;		
		
	private Date created;

	public Item(){		
	}
	
	public Item(String query, Date created) {
		this.query = query;
		this.created = created;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	
}