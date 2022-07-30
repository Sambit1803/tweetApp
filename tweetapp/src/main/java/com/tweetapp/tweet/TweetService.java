package com.tweetapp.tweet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TweetService {
	
	@Autowired
	private TweetRepository tweetRepository;
	
	private static int idCounter=0;
	
	public Tweet find(long id) {
		return tweetRepository.getById(id);
	}
	
	public List<Tweet> findAll(){
		return tweetRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
	}
	
	public List<Tweet> findByLoginId(String loginId){
		return tweetRepository.findByloginId(loginId, Sort.by(Sort.Direction.DESC, "date"));
	}
	
	public Tweet postTweets(Tweet t) {
		return tweetRepository.save(t);
	}
	
	public Tweet updateTweets(Tweet t) {
		if(t.getId()==-1) {
			t.setId(idCounter++);
			return postTweets(t);
		}
		
		else {
			return tweetRepository.save(t);
		}
		
	}
	
	public void deleteTweet(long id) {
		tweetRepository.deleteById(id);
	}
	
	public Tweet updateTweet(long id, Tweet tweet) {
		tweetRepository.deleteById(id);
		
		Tweet updatedTweet = tweetRepository.save(tweet);
		
		return updatedTweet;
	}

}
