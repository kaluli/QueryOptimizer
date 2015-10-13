package com.trabajofinal.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajofinal.model.Item;
import com.trabajofinal.model.Ranking;
import com.trabajofinal.repository.RankingRepository;

@Service("rankingService")
public class RankingServiceImpl implements RankingService {

	@Autowired
	private RankingRepository rankingRepository;
	
	@Transactional
	public Ranking save(Ranking ranking) {
		rankingRepository.save(ranking);
		return null;
	}

	@Transactional
	public void deleteAll() {
		rankingRepository.deleteAll();		
	}

	@Transactional
	public List<Ranking> findAll() {
		return rankingRepository.findAll();				
	}

	
	@Transactional
	public List<Ranking> findByItemId(int item_id) {
		return rankingRepository.findByItemId(item_id);
	}

	@Override
	public List<Ranking> findByUserId(int iduser) {
		return rankingRepository.findByUserId(iduser);
	}

	@Override
	public void deleteByUserId(int iduser) {
		rankingRepository.deleteByUserId(iduser);			
	}

	@Override
	public Double getTimeAverage(int itemID) {
		return rankingRepository.getTimeAverage(itemID);
	}

	

}