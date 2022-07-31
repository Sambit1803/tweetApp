package com.tweetapp.tweet;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger log = LoggerFactory.getLogger(TweetController.class);
	
	@Autowired
	private TweetService tweetService;
	
//	get details of a tweet through tweetId
	
	@GetMapping("/tweet/{id}")
	public Tweet getTweet(@PathVariable long id) {
		Tweet t = tweetService.find(id);
		log.info("Details of tweet of id"+id+" : "+t);
		return t;
	}
	
//	get all tweets
	
	@GetMapping(path="/tweets/all")
	public List<Tweet> getAllTweets(){
			return tweetService.findAll();
		}
	
//	get all tweets of a particular user through loginId
	
	@GetMapping(path="/tweets/{loginId}")
	public List<Tweet> getAllTweetsOfUser(@PathVariable String loginId){
			return tweetService.findByLoginId(loginId);
		}
	
//	post a new tweet by providing tweet details and loginId
	
	
	@PostMapping("/tweets/{loginId}/add")
	public ResponseEntity<Void> postTweets(@PathVariable String loginId,@RequestBody Tweet t) {
			Tweet createdTweet = tweetService.updateTweets(t);
			
			log.info("Posting a new Tweet: "+createdTweet);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTweet.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
	}
	
//	delete a tweet through tweetId
	
	@DeleteMapping("/tweets/delete/{id}")
	public ResponseEntity<Void> deleteTweets(@PathVariable long id){
		tweetService.deleteTweet(id);
		
		log.info("Deleted tweet for id "+id);
		
		return ResponseEntity.noContent().build();
	}
	
//	updating a tweet through tweetId
	
	@PutMapping("/tweets/update/{id}")
	public ResponseEntity<Tweet> updateTweets(@PathVariable long id, @RequestBody Tweet tweet){
		Tweet t= tweetService.updateTweet(id, tweet);
		
		log.info("updating tweet for id "+id+" : "+t);
		
		return new ResponseEntity<Tweet>(t, HttpStatus.OK);
	}

}
