package com.eduhub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduhub.entity.user;
import com.eduhub.repository.userRepository;

@Service
public class userserviceimpl implements userservice {
	
	private userRepository userRepository;
	public userserviceimpl(userRepository userRepository)
	{
		this.userRepository=userRepository;
	}
	
	@Override
	public void saveusers(user user)
	{
		userRepository.save(user);
	}
	
	public List<user> getAllUsers(){
		return userRepository.findAll();
	}
	
	public user getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	public void deleteuser(Long id) {
		 userRepository.deleteById(id);
	}
	
	
}
