package com.trabajofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajofinal.model.Ranking;

@Repository("rankingRepository")
public interface RankingRepository extends JpaRepository<Ranking, Long> {

	
	/*@Query("select s from User s where s.username = :username")
	public User findByUserName(@Param("username") String username);
*/
	/*@Query("select s from UserProfiles s where s.nombre like %:usuario% or s.apellido like %:usuario% or s.user like %:username%")
	public List<User> findbyName(@Param("user") String user);
*/
	/*@Query("select s from User s where s.id = :id")
	public User findbyId(@Param("id") int id); // Por que anda?
	*/
}
