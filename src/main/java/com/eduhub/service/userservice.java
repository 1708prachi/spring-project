package com.eduhub.service;

import java.util.List;

import com.eduhub.entity.user;

public interface userservice {

	void saveusers(user user);
	List<user> getAllUsers();
	user getUserById(Long id);
	void deleteuser(Long id);
	
	
	
}
