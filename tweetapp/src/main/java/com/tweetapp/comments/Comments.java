package com.tweetapp.comments;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Comments")
public class Comments {
	
	private String comment;
	private long tweetId;
	private Date date;
	private String loginId;
	
	public Comments() {
	}
	

	public Comments(String comment, long tweetId, Date date, String loginId) {
		super();
		this.comment = comment;
		this.tweetId = tweetId;
		this.date = date;
		this.loginId = loginId;
	}



	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getTweetId() {
		return tweetId;
	}
	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	@Override
	public String toString() {
		return "Comments [comment=" + comment + ", tweetId=" + tweetId + ", date=" + date + ", loginId=" + loginId
				+ "]";
	}


	
}
