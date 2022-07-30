package com.tweetapp.tweet;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tweet")
public class Tweet {
	
	@Id
	private long id;
	
	private String loginId;
	private String tweets;
	private Date date;
	
	public Tweet() {}
	
	public Tweet(long id, String loginId, String tweets, Date date) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.tweets = tweets;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getTweets() {
		return tweets;
	}

	public void setTweets(String tweets) {
		this.tweets = tweets;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", loginId=" + loginId + ", tweets=" + tweets + ", date=" + date + "]";
	}
	
	
	

}
