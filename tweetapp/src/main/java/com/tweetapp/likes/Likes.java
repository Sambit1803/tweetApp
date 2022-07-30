package com.tweetapp.likes;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Likes")
public class Likes {
	
	private long id;
	private long likes;
	
	
	public Likes() {
	}




	public Likes(long id, long likes) {
		super();
		this.id = id;
		this.likes = likes;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getLikes() {
		return likes;
	}


	public void setLikes(long likes) {
		this.likes = likes;
	}




	@Override
	public String toString() {
		return "Likes [id=" + id + ", likes=" + likes + "]";
	}
	
	





	

}
