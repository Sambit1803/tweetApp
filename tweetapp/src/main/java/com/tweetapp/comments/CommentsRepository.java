package com.tweetapp.comments;

import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends MongoRepository<Comments, String> {
	
	@Query("{'tweetId' : ?0}")
	List<Comments> getAllBytweetId(long tweetId, Sort sort);
}
