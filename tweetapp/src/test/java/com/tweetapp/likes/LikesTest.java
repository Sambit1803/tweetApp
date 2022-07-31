package com.tweetapp.likes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class LikesTest {
	
	@Autowired
	private LikesController likesController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(likesController).isNotNull();
	}
	
	@Test
	public void getAll() throws Exception {
		assertThat(likesController.getAll()).isNotNull();
	}
	
	@Test
	public void getlikeCount() throws Exception {
		assertThat(likesController.getLikeCount(4)).isNotNull();
	}
	
	@Test
	public void updateLikes() throws Exception {
		Likes likes = new Likes(10,1); 
		ResponseEntity<Likes> value = new ResponseEntity<Likes>(likes, HttpStatus.OK);
		assertThat(likesController.updateLikes(-1, "abc.x", likes)).isEqualTo(value);
	}
	
	@Test
	public void deleteLike() throws Exception{
		ResponseEntity<Void> value = ResponseEntity.noContent().build();
		assertThat(likesController.Delete(0)).isEqualTo(value);
	}
	
	
	

}
