package com.trabajofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajofinal.model.Item;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	/*@Query("select s from UserProfiles s where s.nombre like %:usuario% or s.apellido like %:usuario% or s.user like %:username%")
	public List<User> findbyName(@Param("user") String user);
	 */
	@Query("select s from Item s where s.id = :id")
	public Item findById(@Param("id") int id); 
	
}
