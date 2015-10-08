package com.trabajofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajofinal.model.Item;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {
		
	@Query("select s from Item s where s.id = :id")
	public Item findById(@Param("id") int id);

	@Query("select s from Item s where s.id != :id")	
	public List<Item> findQueriesAlternativas(@Param("id") int id); 
	
}
