package com.sportdec.application.domain;

public class TweetObject {

	private long id;
	private String created_at;
	private String text;
	
	public String toString() {
		return String.format("id: {%d}, created_at: {%s}, text: {%s}", id, created_at, text);
	}
}
