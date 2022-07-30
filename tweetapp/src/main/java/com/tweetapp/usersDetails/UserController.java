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

@CrossOrigin("http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/tweets/register")
	public ResponseEntity<Void> register(@RequestBody Users user) {
		Users createdUser = userService.register(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{loginId}").buildAndExpand(createdUser.getLoginId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/tweets/users/all")
	public List<Users> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/tweets/user/search/{str}")
	public List<Users> searchByRegex(@PathVariable String str){
		return userService.searchByRegex(str);
	}
	
	@GetMapping("/tweets/user/{loginId}")
	public Users getUser(@PathVariable String loginId) {
		return userService.getUser(loginId);
	}
	
	@PutMapping("/tweets/{loginId}/forgot")
	public ResponseEntity<Users> updatePassword(@PathVariable String loginId, @RequestBody Users user){
		Users u = userService.updatePassword(loginId, user);
		return new ResponseEntity<Users>(u, HttpStatus.OK);
	}

}
