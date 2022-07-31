package com.tweetapp.likes;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
public class LikesController {
	
	Logger log = LoggerFactory.getLogger(LikesController.class);
	
	@Autowired
	private LikesService likesService;
	
//	get likes count for a particular tweetId
	
	@GetMapping("/tweet/{id}/likes")
	public Likes getLikeCount(@PathVariable long id) {
		
		Likes l = likesService.getLikesCount(id);
		log.info("Details of likes for tweetId "+id+" : "+l);
		return l;
		
	}
	
//	get details of likes for all tweetId
	
	@GetMapping("/tweet/all/likes")
	public List<Likes> getAll(){
		return likesService.getAll();
	}
	
//	updating likes (like/dislike)
	
	@PutMapping("/tweet/{id}/likesUpdate/{loginId}")
	public ResponseEntity<Likes> updateLikes(@PathVariable long id,@PathVariable String loginId, @RequestBody Likes likes) {
		Likes updatedLikes = likesService.updateLikes(id, loginId, likes);
		
		log.info("Updating likes "+updatedLikes);
		
		return new ResponseEntity<Likes>(updatedLikes, HttpStatus.OK);
	}
	
//	delete details of a particular tweetId
	
	@DeleteMapping("/tweet/{id}/delete")
	public ResponseEntity<Void> Delete(@PathVariable long id){
		likesService.Delete(id);
		log.info("Deleting details of tweetId : "+id);
		return ResponseEntity.noContent().build();
	}

}
