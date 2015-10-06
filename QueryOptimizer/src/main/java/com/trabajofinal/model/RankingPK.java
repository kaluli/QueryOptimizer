package com.trabajofinal.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class RankingPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id", nullable = false)
	private int user_id;
	
	@Column(name = "item_id", nullable = false)
	private int item_id;	
	
	
	public RankingPK(){
		
	}
	
	public RankingPK(int user_id, int item_id) {		
		this.user_id = user_id;
		this.item_id = item_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + item_id;
		result = prime * result + user_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RankingPK other = (RankingPK) obj;
		if (item_id != other.item_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	
	
	
	
}
