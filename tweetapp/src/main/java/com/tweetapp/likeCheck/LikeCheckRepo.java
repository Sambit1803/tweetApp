package com.tweetapp.likeCheck;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface LikeCheckRepo extends MongoRepository<LikeCheck, String> {
	
	@Query(value="{'loginId' : ?0, 'tweetId' : ?1}", delete=true)
	LikeCheck deleteByloginId(String loginId, long tweetId);

}
