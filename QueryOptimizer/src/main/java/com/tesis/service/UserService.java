package com.tesis.service;

import java.util.List;

import com.tesis.model.User;

public interface UserService {
	public User save(User user);
	public User findByLogin(String user, String password);
	public User findByUserName(String user);
	public User findById(int id);
	//public List<User> findbyName(String nombre);
	public List<User> findAll();

}
