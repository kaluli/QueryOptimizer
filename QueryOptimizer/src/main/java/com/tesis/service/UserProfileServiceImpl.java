package com.tesis.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesis.model.User;
import com.tesis.model.UserProfile;
import com.tesis.repository.UserProfileRepository;
import com.tesis.repository.UserRepository;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Transactional
	public UserProfile save(UserProfile user_profile) {
//		return userProfileRepository.save(user_profile);
		return user_profile;
	}
	
	
	/*@Override
	public UserProfile findByLogin(String user, String password) {	
		UserProfile usu = userProfileRepository.findByUserName(user);
		
		if(usu != null && usu.getPassword().equals(password)) {
			return usu;
		} 
		
		return null;		
	}*/
	
	/*@Override
	public UserProfile findByUserName(String user) {
		UserProfile usu = userProfileRepository.findByUserName(user);
		
		if(usu != null) {
			return usu;
		}
		
		return null;
	}*/

	/*@Override
	public List<UserProfile> findAll() {
		return userProfileRepository.findAll();
	}*/

	/*	@Override
	public List<User> findbyName(String username) {		
		return userRepository.findbyName(username);
	}*/

//	@Override
	/*public UserProfile findById(int id) {
		return userProfileRepository.findbyId(id);
	}*/

}
