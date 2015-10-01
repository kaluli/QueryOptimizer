package com.trabajofinal.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="ranking")
@IdClass(value=RankingPK.class)
public class Ranking{
	
	@Id
	private int user_id;

	@Id
	private int item_id;
	
	@Column(name = "ranking", nullable = false)
	private Float ranking;

	public Ranking(){}
	
	public Ranking(int user_id, int item_id, Float ranking) {
		this.user_id = user_id;
		this.item_id = item_id;
		this.ranking = ranking;
	}

	public Float getRanking() {
		return ranking;
	}

	public void setRanking(Float  ranking) {
		this.ranking = ranking;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	


}
