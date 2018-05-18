package com.stackroute.activitystream.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.repository.UserRepository;
import com.stackroute.activitystream.service.UserService;
import com.stackroute.activitystream.service.UserServiceImpl;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(userService.list(), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<User> getUserDetails(@PathVariable("username") String username) {
		User user = userService.get(username);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User u = userService.get(user.getUsername());
		if (u != null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		} else {
			userService.save(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/{username}")
	public ResponseEntity<User> updateUserdetails(@PathVariable("username") String username, @RequestBody User user) {
		User currentUser = userService.get(username);
		if (currentUser != null) {
			currentUser.setName(user.getName());
			currentUser.setPassword(user.getPassword());
			userService.update(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
}