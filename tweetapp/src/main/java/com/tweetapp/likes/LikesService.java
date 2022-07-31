package com.tweetapp.likes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.likeCheck.LikeCheck;
import com.tweetapp.likeCheck.LikeCheckRepo;

@Service
public class LikesService {
	
	@Autowired
	private LikesRepository likesRepository;
	
	@Autowired
	private LikeCheckRepo likeCheckRepo;
	
	public Likes getLikesCount(long id) {
		return likesRepository.getLikeById(id);
	}
	
	public List<Likes> getAll() {
		return likesRepository.findAll();
	}
	
	public Likes updateLikes(long id,String loginId, Likes likes) {;
		
		Likes li=likes;
		if(id==-1) {
			likeCheckRepo.save(new LikeCheck(loginId, li.getId()));
			return likesRepository.save(likes);
		}
		else {
			if(check(id, loginId)) {
				long l=li.getLikes()-2;
				li.setLikes(l);
				likeCheckRepo.deleteByloginId(loginId, id);
				likesRepository.deleteById(id);
				return likesRepository.save(li);
				
			}
			else {
				likeCheckRepo.save(new LikeCheck(loginId, id));
				likesRepository.deleteById(id);
				return likesRepository.save(li);
			}
			
			
		}
	}
	
	public Likes Delete(long id) {
		return likesRepository.deleteById(id);
	}
	
	public boolean check(long tweetId, String loginId) {
		List<LikeCheck> l = likeCheckRepo.findAll();
		
		for(int i=0;i<l.size();i++) {
			if(tweetId==l.get(i).getTweetId() && loginId.equals(l.get(i).getLoginId())) {
				return true;
			}
				
		}
		return false;
	}

}
