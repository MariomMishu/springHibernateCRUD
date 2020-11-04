package com.springPractice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springPractice.models.User;

@Service
public class UserService {
	private List<User> users;
	
	public UserService(List<User> users) {
		this.users=users;
	}
	public void showAll() {
		
		users.forEach(System.out::println);
	}
	public User createUser(String name) {
		var user = new User();
		user.setName(name);
		users.add(user);
		return user;
	}
}
