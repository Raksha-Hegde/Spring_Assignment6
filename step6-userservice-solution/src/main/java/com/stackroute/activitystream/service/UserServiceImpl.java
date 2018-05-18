package com.stackroute.activitystream.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.repository.UserRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn�t currently 
* provide any additional behavior over the @Component annotation, but it�s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public boolean save(User user) {
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * * This method should be used to update an existing user. Call the
	 * corresponding method of Respository interface. *
	 */ public boolean update(User user) {
		try {
			User user1 = userRepository.findOne(user.getUsername());
			if (user1 != null) {
				userRepository.save(user);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * * This method should be used to delete an existing user. Call the
	 * corresponding method of Respository interface. *
	 */ public boolean delete(User user) {
		try {
			User user1 = userRepository.findOne(user.getUsername());
			if (user1 != null) {
				userRepository.delete(user);
				;
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * * This method should be used to list all users. Call the corresponding method
	 * of Respository interface. *
	 */ public List<User> list() {
		return (List<User>) userRepository.findAll();
	}

	/*
	 * * This method should be used to validate a user using password. Call the
	 * corresponding method of Respository interface. *
	 */ public boolean validate(String username, String password) {
		boolean status = false;
		User user = userRepository.validate(username, password);
		if (user != null) {
			status = true;
		}
		return false;
	}

	/*
	 * * This method should be used to get a user by username. Call the
	 * corresponding method of Respository interface.
	 */ public User get(String username) {
		return userRepository.findOne(username);
	}

	/*
	 * * This method is used to check whether a user with a specific username
	 * exists. Call the corresponding method of Respository interface.
	 */ public boolean exists(String username) {
		return userRepository.exists(username);
	}
}