package com.stackroute.activitystream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.model.UserCircle;
import com.stackroute.activitystream.service.UserCircleService;


/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@RequestMapping("/api/usercircle")
public class UserCircleController {
	/*
	 * Autowiring should be implemented for the UserCircleService, UserCircle.
	 * Please note that we should not create any object using the new keyword
	 */
	@Autowired
	UserCircle userCircle;

	@Autowired
	UserCircleService userCircleService;;

	/*
	 * Define a handler method which will add a user to a circle.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations:
	 * 
	 * 1. 200(OK) - If the user is added to the circle 2. 500(INTERNAL SERVER ERROR)
	 * - If there are any errors 3. 409(CONFLICT) - if the user is already added to
	 * the circle
	 * 
	 * This handler method should map to the URL
	 * "/api/usercircle/addToCircle/{username}/{circleName}" using HTTP PUT method"
	 * where "username" should be replaced by a valid username without {} and
	 * "circleName" should be replaced by a valid circle name without {}
	 */
	@PutMapping(value = "/addToCircle/{username}/{circleName}")
	public ResponseEntity<?> addToCircle(@PathVariable("username") String username,
			@PathVariable("circleName") String circleName) {
		userCircle = userCircleService.get(username, circleName);
		if (userCircle != null) {
			return (new ResponseEntity<>(HttpStatus.CONFLICT));
		} else if (userCircleService.addUser(username, circleName)) {
			return (new ResponseEntity<>(HttpStatus.OK));
		} else {
			return (new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		}

	}

	/*
	 * Define a handler method which will remove a user from a circle.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user is remove from the circle 2.
	 * 500(INTERNAL SERVER ERROR) - If there are any errors
	 * 
	 * This handler method should map to the URL
	 * "/api/usercircle/removeFromCircle/{username}/{circleName}" using HTTP PUT
	 * method" where "username" should be replaced by a valid username without {}
	 * and "circleName" should be replaced by a valid circle name without {}
	 */
	@PutMapping(value = "/removeFromCircle/{username}/{circleName}")
	public ResponseEntity<?> removeFromCircle(@PathVariable("username") String username,
			@PathVariable("circleName") String circleName) {
		if (userCircleService.removeUser(username, circleName)) {
			return (new ResponseEntity<>(HttpStatus.OK));
		} else {
			return (new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		}

	}

	/*
	 * Define a handler method which will get us the subscribed circles by a user.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user is added to the circle
	 * 
	 * This handler method should map to the URL
	 * "/api/usercircle/searchByUser/{username}" using HTTP GET method where
	 * "username" should be replaced by a valid username without {}
	 */

	@GetMapping(value = "/searchByUser/{username}")
	public ResponseEntity<List<String>> searchByUser(@PathVariable("username") String username) {
		return new ResponseEntity<List<String>>(userCircleService.getMyCircles(username), HttpStatus.OK);
	}
}
