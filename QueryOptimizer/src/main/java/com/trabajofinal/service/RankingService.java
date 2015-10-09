package com.trabajofinal.service;

import java.util.List;

import com.trabajofinal.model.Ranking;

public interface RankingService {
	public Ranking save(Ranking query);
	public List<Ranking> findAll();
	public void deleteAll();
	public List<Ranking> findByItemId(int itemId);
	public List<Ranking>findByUserId(int iduser);
	public void deleteByUserId(int iduser);
	
	
}
