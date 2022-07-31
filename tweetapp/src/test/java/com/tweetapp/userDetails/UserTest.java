package com.tweetapp.userDetails;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tweetapp.usersDetails.UserController;
import com.tweetapp.usersDetails.Users;

@SpringBootTest 
public class UserTest {
	
	@Autowired
	private UserController userController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}
	
	@Test
	public void registerUser() throws Exception{
		Users user=new Users("abc","x","abc@h.com","abc.x","pass","1111111111");
		URI location = new URI("http://localhost/abc.x");
		ResponseEntity<Void> value = ResponseEntity.created(location).build();
		System.out.println(value+" "+user);
		assertThat(userController.register(user)).isEqualTo(value);
		
	}
	
	@Test
	public void authentication() throws Exception{
		assertThat(userController.authencticate("abc.x", "pass")).isTrue();
	}
	
	@Test
	public void getAllUser() throws Exception{
		assertThat(userController.getAllUsers()).isNotNull();
	}
	
	@Test
	public void searchByRegex() throws Exception{
		assertThat(userController.searchByRegex("abc")).isNotNull();
	}
	
	@Test
	public void getUser() throws Exception{
		assertThat(userController.getUser("abc")).isNull();
	}
	
	@Test
	public void forgotPassword() throws Exception{
		Users user = new Users("abc","x","abc@h.com","abc.x","1222","1111111111");
		ResponseEntity<Users> value=new ResponseEntity<Users>(user, HttpStatus.OK);
		assertThat(userController.updatePassword("abc.x", user)).isEqualTo(value);
	}

}
