package com.trabajofinal.service;

import java.util.List;

import com.trabajofinal.model.Ranking;

public interface RankingService {
	public Ranking save(Ranking query);
	public List<Ranking> findAll();
	public void deleteAll();
	public Ranking findByItemId(int itemId);
	
	
}
