package com.tesis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tesis.model.User;

@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<User, Long> {
	
	@Query("select s from User s where s.username = :username")
	public User findByUserName(@Param("username") String username);

	@Query("select s from User s where s.id = :id")
	public User findbyId(@Param("id") int id); // Por que anda?
	
}
