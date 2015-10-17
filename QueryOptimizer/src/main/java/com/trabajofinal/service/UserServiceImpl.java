package com.trabajofinal.service;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.trabajofinal.model.User;
import com.trabajofinal.repository.UserRepository;

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
		if (usu != null){		
			Hasher hasher = Hashing.md5().newHasher();
			hasher.putString(password);			
			String hashedPass = hasher.hash().toString();		
			if(usu.getPassword().equals(hashedPass)) {
				return usu;
			}				
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
