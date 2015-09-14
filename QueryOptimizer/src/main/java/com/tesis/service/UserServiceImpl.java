package com.tesis.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesis.model.User;
import com.tesis.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	
	public User findByLogin(String user, String password) {	
		User usu = userRepository.findByUserName(user);
		
		if(usu != null && usu.getPassword().equals(password)) {
			return usu;
		} 
		
		return null;		
	}
	
	public User findByUserName(String user) {
		User usu = userRepository.findByUserName(user);
		
		if(usu != null) {
			return usu;
		}
		
		return null;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	/*	@Override
	public List<User> findbyName(String username) {		
		return userRepository.findbyName(username);
	}*/
	
	public User findById(int id) {
		return userRepository.findbyId(id);
	}

}
