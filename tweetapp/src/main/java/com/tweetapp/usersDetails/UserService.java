package com.tweetapp.usersDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Users register(Users user) {
		return userRepository.save(user);
	}
	
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Users getUser(String loginId) {
		return userRepository.findByloginId(loginId);
	}
	
	public Users updatePassword(String loginId,Users user) {
		userRepository.DeleteByloginId(loginId);
		Users updatedUser = userRepository.save(user);
		
		return updatedUser;
	}
	
	public List<Users> searchByRegex(String str){
		return userRepository.searchByRegex(str);
	}
	
	public boolean authenticate(String loginId, String password) {
		Users user = userRepository.findByloginId(loginId);
		
		if(user==null)
			return false;
		
		if(user.getPassword().equals(password)){
			return true;
		}
		else
			return false;
	}

}
