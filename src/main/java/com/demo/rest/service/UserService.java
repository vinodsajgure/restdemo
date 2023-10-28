package com.demo.rest.service;

import java.util.List;
import java.util.Optional;

import com.demo.rest.entity.User;

public interface UserService {

	public List<User> getUsers();

	public void saveUser(User user);

	public Optional<User> getUserById(int userId);
	
	public User updateUserById(int id,User user) throws Exception;
	
	public void deleteById(int userId);;
}
