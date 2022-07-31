package com.tweetapp.tweetsTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tweetapp.tweet.Tweet;
import com.tweetapp.tweet.TweetController;

@SpringBootTest
public class TweetsTest {
	
	@Autowired
	private TweetController tweetController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(tweetController).isNotNull();
	}
	
	@Test
	public void getTweet() throws Exception{
		assertThat(tweetController.getTweet(16)).isNotNull();
	}
	
	@Test
	public void getAllTweet() throws Exception{
		assertThat(tweetController.getAllTweets()).isNotNull();
	}
	
	@Test
	public void getAllTweetsOfUser() throws Exception{
		assertThat(tweetController.getAllTweetsOfUser("abc.x")).isNotNull();
	}
	
	@Test
	public void postTweet() throws Exception{
		Tweet t=new Tweet(16,"abc.x","Hi !!", new Date());
		URI location = new URI("http://localhost/16");
		ResponseEntity<Void> value = ResponseEntity.created(location).build();
		System.out.print(t);
		assertThat(tweetController.postTweets("abc.x", t)).isEqualTo(value);
		
	}
	
	@Test
	public void deleteTweet() throws Exception{
		ResponseEntity<Void> value = ResponseEntity.noContent().build();
		assertThat(tweetController.deleteTweets(2)).isEqualTo(value);
		
	}
	
	@Test
	public void updateTweet() throws Exception{
		Tweet t=new Tweet(10,"abc.x","Hi !! (Updated)", new Date());
		ResponseEntity<Tweet> value = new ResponseEntity<Tweet>(t, HttpStatus.OK);
		assertThat(tweetController.updateTweets(10, t)).isEqualTo(value);
		
	}

}
