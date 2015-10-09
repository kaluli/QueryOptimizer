package com.trabajofinal.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajofinal.model.Ranking;

@Repository("rankingRepository")
public interface RankingRepository extends JpaRepository<Ranking, Long> {
	
	/*@Query("select s from UserProfiles s where s.nombre like %:usuario% or s.apellido like %:usuario% or s.user like %:username%")
	public List<User> findbyName(@Param("user") String user);
*/
	@Query("select s from Ranking s where s.item_id = :item_id limit 1")
	public Ranking findByItemId(@Param("item_id") int item_id);

	@Query("select s from Ranking s where s.user_id = :user_id limit 1")
	public Ranking findByUserId(@Param("user_id") int user_id);
	
	@Modifying
	@Transactional
	@Query("delete from Ranking s where  s.user_id = :user_id ")
	public void deleteByUserId(@Param("user_id") int user_id);
}
