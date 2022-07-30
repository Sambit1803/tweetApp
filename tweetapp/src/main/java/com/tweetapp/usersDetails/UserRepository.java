package com.tweetapp.usersDetails;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.Query;

@Repository
public interface UserRepository extends MongoRepository<Users, String>{
	
	
	@Query("{'loginId' : ?0}")
	Users findByloginId(String loginId);
	
	@Query(value="{'loginId' : ?0}", delete=true)
	Users DeleteByloginId(String loginId);
	
	@Query("{'loginId':{'$regex':'?0','$options':'i'}}")  
    List<Users> searchByRegex(String str);

}
