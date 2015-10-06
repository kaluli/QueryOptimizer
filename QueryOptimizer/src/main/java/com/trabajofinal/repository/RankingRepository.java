package com.trabajofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajofinal.model.Ranking;

@Repository("rankingRepository")
public interface RankingRepository extends JpaRepository<Ranking, Long> {
	
	/*@Query("select s from UserProfiles s where s.nombre like %:usuario% or s.apellido like %:usuario% or s.user like %:username%")
	public List<User> findbyName(@Param("user") String user);

	/*@Query("select s from User s where s.id = :id")
	public User findbyId(@Param("id") int id); // Por que anda?
	*/
}
