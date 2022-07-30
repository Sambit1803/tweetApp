package com.tweetapp.likeCheck;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("LikeCheck")
public class LikeCheck {
	
	private String loginId;
	private long tweetId;
	
	public LikeCheck(String loginId, long tweetId) {
		super();
		this.loginId = loginId;
		this.tweetId = tweetId;
	}
	public LikeCheck() {
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public long getTweetId() {
		return tweetId;
	}
	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}
	@Override
	public String toString() {
		return "LikeCheck [loginId=" + loginId + ", tweetId=" + tweetId + "]";
	}
	
	

}
