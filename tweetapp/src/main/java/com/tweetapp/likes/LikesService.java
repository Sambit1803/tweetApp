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
	
	public Likes updateLikes(long id,String loginId, Likes likes) {
		
		System.out.print("\nBefore: "+likesRepository.findAll()+" likeCheckRepo: "+ likeCheckRepo.findAll());
		
		Likes li=likes;
		System.out.print("\nid : "+id+" loginId: "+loginId);
		if(id==-1) {
			System.out.print("Id==-1");
			likeCheckRepo.save(new LikeCheck(loginId, li.getId()));
			return likesRepository.save(likes);
		}
		else {
			//check in likecheckRepo
			//if present (likes-=1 , delete(loginId))
			if(check(id, loginId)) {
				System.out.print("Present");
				long l=li.getLikes()-2;
				li.setLikes(l);
				likeCheckRepo.deleteByloginId(loginId, id);
				likesRepository.deleteById(id);
				return likesRepository.save(li);
				
			}
			
			//else not present like+=1 , add(loginId)
			else {
				System.out.print("Absent");
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
			System.out.print("\n tweetId :"+tweetId+" l.get(i).getTweetId(): "+l.get(i).getTweetId()+" loginId: "
					+loginId+" l.get(i).getLoginId(): "+l.get(i).getLoginId());
			if(tweetId==l.get(i).getTweetId() && loginId.equals(l.get(i).getLoginId())) {
				System.out.print("\nPresent\n");
				return true;
			}
				
		}
		return false;
	}

}
