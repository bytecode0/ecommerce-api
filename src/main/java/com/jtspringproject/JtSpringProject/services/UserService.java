package com.jtspringproject.JtSpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.jtspringproject.JtSpringProject.dao.UserDao;
import com.jtspringproject.JtSpringProject.models.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public List<User> getUsers() {
		return this.userDao.getAllUser();
	}
	
	public User addUser(User user) {
		try {
			return this.userDao.saveUser(user);
		} catch (DataIntegrityViolationException e) {
			// handle unique constraint violation, e.g., by throwing a custom exception
			throw new RuntimeException("Add user error");
		}
	}
	
	public User checkLogin(String email, String password) {
		return this.userDao.getUser(email, password);
	}

	public boolean checkUserExists(String email) {
		return this.userDao.userExists(email);
	}

	public User getUserByEmail(String email) {
	        return userDao.getUserByEmail(email);
	    }
}
