package com.tweetapp.usersDetails;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin("http://localhost:4200")
@RestController
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
//	register user
	
	@PostMapping("/tweets/register")
	public ResponseEntity<Void> register(@RequestBody Users user) {
		
		Users createdUser = userService.register(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{loginId}").buildAndExpand(createdUser.getLoginId()).toUri();
		
		log.info("Registering user :"+ createdUser);
		
		return ResponseEntity.created(uri).build();
		
		
	}
	
//	get tweets of all user
	
	@GetMapping("/tweets/users/all")
	public List<Users> getAllUsers(){
		return userService.getAllUsers();
	}
	
//	search users through partial/full loginId(username) and 
//	returns the list of users matching the regular expression
	
	@GetMapping("/tweets/user/search/{str}")
	public List<Users> searchByRegex(@PathVariable String str){
		return userService.searchByRegex(str);
	}
	
//	get the details of a particular user through loginId
	
	@GetMapping("/tweets/user/{loginId}")
	public Users getUser(@PathVariable String loginId) {
		return userService.getUser(loginId);
	}
	
//	used to update password of a particular user 
//	by providing loginId and new password
	
	@PutMapping("/tweets/{loginId}/forgot")
	public ResponseEntity<Users> updatePassword(@PathVariable String loginId, @RequestBody Users user){
		Users u = userService.updatePassword(loginId, user);
		log.info("Password Updated for user "+u);
		return new ResponseEntity<Users>(u, HttpStatus.OK);
	}
	
//	authenticate user and 
//	return true if user is already registered
//	else false
	
	@GetMapping("/authenticate/{loginId}/{password}")
	public boolean authencticate(@PathVariable String loginId,@PathVariable String password) {
		return userService.authenticate(loginId, password);
	}

}
