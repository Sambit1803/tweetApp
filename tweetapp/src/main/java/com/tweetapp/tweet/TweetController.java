package com.tweetapp.tweet;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TweetController {
	
	@Autowired
	private TweetService tweetService;
	
	@GetMapping("/tweet/{id}")
	public Tweet getTweet(@PathVariable long id) {
		Tweet t = tweetService.find(id);
		return t;
	}
	
	@GetMapping(path="/tweets/all")
	public List<Tweet> getAllTweets(){
			return tweetService.findAll();
		}
	
	@GetMapping(path="/tweets/{loginId}")
	public List<Tweet> getAllTweetsOfUser(@PathVariable String loginId){
			return tweetService.findByLoginId(loginId);
		}
	
	
	@PostMapping("/tweets/{loginId}/add")
	public ResponseEntity<Void> postTweets(@PathVariable String loginId,@RequestBody Tweet t) {
			Tweet createdTweet = tweetService.updateTweets(t);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTweet.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/tweets/delete/{id}")
	public ResponseEntity<Void> deleteTweets(@PathVariable long id){
		tweetService.deleteTweet(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/tweets/update/{id}")
	public ResponseEntity<Tweet> updateTweets(@PathVariable long id, @RequestBody Tweet tweet){
		Tweet t= tweetService.updateTweet(id, tweet);
		
		
		return new ResponseEntity<Tweet>(t, HttpStatus.OK);
	}

}
