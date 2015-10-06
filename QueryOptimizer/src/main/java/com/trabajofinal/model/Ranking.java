package com.trabajofinal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.swing.ListModel;

@Entity
@Table(name="rankings")
@IdClass(value=RankingPK.class)
public class Ranking{
	
	@Id
	private int user_id;

	@Id
	private int item_id;
	
	@Column(name = "ranking", nullable = false)
	private Float ranking;
	
	private Double time;
	
	private Date created;

	public Ranking(){}
	
	public Ranking(int user_id, int item_id, Float ranking,Double time, Date created) {		
		this.user_id = user_id;
		this.item_id = item_id;
		this.ranking = (float)1;
		this.time    = time;
		this.created = created;
	}
			
	
	
	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Float getRanking() {
		return ranking;
	}

	public void setRanking(Float  ranking) {
		this.ranking = ranking;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getItemId() {
		return item_id;
	}

	public void setItemId(int item_id) {
		this.item_id = item_id;
	}

	
	
	/*@OneToMany(targetEntity=Item.class, mappedBy="id", fetch=FetchType.EAGER)
	private List<Item> Item;
*/
	
}
