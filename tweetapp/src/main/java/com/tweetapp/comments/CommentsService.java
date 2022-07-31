package com.tweetapp.comments;

import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	public List<Comments> getAll(long tweetId){
		return commentsRepository.getAllBytweetId(tweetId, Sort.by(Sort.Direction.DESC, "date"));
	}
	
	public Comments postComment(Comments comments) {
		return commentsRepository.save(comments);
	}
	
}
