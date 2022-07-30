package com.tweetapp.likes;

import java.util.List;

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
	
	@Autowired
	private LikesService likesService;
	
	@GetMapping("/tweet/{id}/likes")
	public Likes getLikeCount(@PathVariable long id) {
		
		Likes l = likesService.getLikesCount(id);
		System.out.print(l);
		return l;
		
	}
	
	@GetMapping("/tweet/all/likes")
	public List<Likes> getAll(){
		return likesService.getAll();
	}
	
	@PutMapping("/tweet/{id}/likesUpdate/{loginId}")
	public ResponseEntity<Likes> updateLikes(@PathVariable long id,@PathVariable String loginId, @RequestBody Likes likes) {
		Likes updatedLikes = likesService.updateLikes(id, loginId, likes);
		
		System.out.print("After:"+updatedLikes);
		
		
		return new ResponseEntity<Likes>(updatedLikes, HttpStatus.OK);
	}
	
	@DeleteMapping("/tweet/{id}/delete")
	public ResponseEntity<Void> Delete(@PathVariable long id){
		likesService.Delete(id);
		return ResponseEntity.noContent().build();
	}

}
