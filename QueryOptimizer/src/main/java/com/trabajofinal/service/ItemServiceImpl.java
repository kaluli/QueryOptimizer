package com.trabajofinal.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajofinal.model.Item;
import com.trabajofinal.repository.ItemRepository;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public Item save(Item item) {
		itemRepository.save(item);
		return null;
	}
	
	@Transactional
	public Item findByQuery(String item) {
		return itemRepository.findByQuery(item);
	}
	
	@Transactional
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
	
	@Transactional
	public Item findById(int id) {
		return itemRepository.findById(id);
	}

	@Override
	public List<Item> findQueriesAlternativas(int itemId) {
		return itemRepository.findQueriesAlternativas(itemId);
	}

}