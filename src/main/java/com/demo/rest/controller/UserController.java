package com.demo.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.rest.entity.User;
import com.demo.rest.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;
	
	
// GET ALL USERS
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody()
	public ResponseEntity<List<User>> getUsers(@RequestHeader(value = "X-API-KEY", required = false) String apiKey) {
		System.out.println(apiKey);
		List<User> listProducts = service.getUsers();

		if (listProducts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listProducts);

	}
	
	
// SAVE USER	

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, consumes = (MediaType.APPLICATION_JSON_VALUE))
	@ResponseBody()
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		service.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
	
	
// GET USER BY ID
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody()
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
		Optional<User> user = service.getUserById(userId);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {

			return ResponseEntity.notFound().build();
		}

	}
	
	
//UPDATE USER BY ID
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE,
																				  MediaType.APPLICATION_XML_VALUE })
	@ResponseBody()
	public ResponseEntity<Object> updateUserById(@PathVariable("userId") Integer userId, @RequestBody User user) {
		try {
			service.updateUserById(userId, user);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}
	
	
//DELETE BY ID
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseBody()
	public void deleteById(@PathVariable("userId") Integer userId) {
		service.deleteById(userId);

	}

}
