package com.demo.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.rest.dao.UserRepository;
import com.demo.rest.entity.User;
import com.demo.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Override
	public void saveUser(User user) {
		repo.save(user);

	}

	@Override
	public Optional<User> getUserById(int userId) {
		List<User> user = repo.findByEmail("vin@gamil.com");
		List<User> user1 = repo.findByFirstNameAndLastName("Nikita", "Mahajan");
		List<User> user2 = repo.findByFirstNameOrLastName("manohar", "sajgure");
		List<User> user3= repo.findByMobile(7709993825L);

		System.out.println(user);
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
		return repo.findById(userId);
	}

	@Override
	public User updateUserById(int id, User user) throws Exception {
		Optional<User> dbuser = repo.findById(id);
		if (!dbuser.isPresent()) {
			throw new Exception();

		} else {

			user.setUserId(dbuser.get().getUserId());
			return repo.save(user);

		}

	}

	@Override
	public void deleteById(int userId) {
		repo.deleteById(userId);

	}

}
