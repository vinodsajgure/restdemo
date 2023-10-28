package com.demo.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.rest.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	public List<User> findByEmail(String email123);
	
	public List<User> findByFirstNameAndLastName(String fanme, String lanme);
	
	public List<User> findByFirstNameOrLastName(String fanme, String lanme);
	
	public List<User> findByMobile(Long mobile);

}
