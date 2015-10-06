package com.trabajofinal.service;

import java.util.List;

import com.trabajofinal.model.Item;

public interface ItemService {
	public Item save(Item item);
	public Item findById(int id);
	public List<Item> findAll();

}
