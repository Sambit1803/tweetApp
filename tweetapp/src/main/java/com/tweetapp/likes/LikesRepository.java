package com.tweetapp.likes;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends MongoRepository<Likes, String> {
	
	@Query("{'id' : ?0}")
	Likes getLikeById(long id);
	
	@Query(value="{'id' : ?0}", delete=true)
	Likes deleteById(long id);

}
