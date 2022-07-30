package com.tweetapp.tweet;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, Long>{
	
	@Query("{'loginId' : ?0}")
	List<Tweet> findByloginId(String loginId, Sort sort);
	
//	@Query(value="{'id' : ?0}", delete=true)
//	Tweet DeleteById(String id);
	
	@Query("{'id' : ?0}")
	Tweet getById(long id);

}
