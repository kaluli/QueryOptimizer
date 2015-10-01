package com.trabajofinal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajofinal.model.Consulta;
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

	

}