package com.tweetapp.comments;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("http://localhost:4200")
@RestController
public class CommentsController {
	
	Logger log = LoggerFactory.getLogger(Comments.class);
	
	@Autowired
	private CommentsService commentsService;
	
//	get all comments for a particular tweetId
	
	@GetMapping("/comments/{tweetId}")
	public List<Comments> getAll(@PathVariable long tweetId){
		return commentsService.getAll(tweetId);
	}
	
//	post a comment
	
	@PostMapping("/tweets/reply/{tweetId}")
	public ResponseEntity<Void> postComment(@PathVariable long tweetId, @RequestBody Comments comments){
		Comments postedComment = commentsService.postComment(comments);
		
		log.info("Posted a comment for tweetId "+tweetId+" : "+postedComment);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postedComment.getComment()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
