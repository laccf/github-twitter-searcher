package com.sportdec.application.service;

import java.util.List;

import com.sportdec.application.connectionProperty.TwitterProperties;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterService {

	private static final String MENTION_TAG = "@";
	private Twitter twitterInstance;
	
	public TwitterService(TwitterProperties properties) {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(properties.getConsumerKey());
		builder.setOAuthConsumerSecret(properties.getConsumerSecret());
		builder.setOAuthAccessToken(properties.getAccessToken());
		builder.setOAuthAccessTokenSecret(properties.getAccessTokenSecret());
		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);
		this.twitterInstance = factory.getInstance();
	}

	public List<Status> searchTweet(String stringToSearch) throws TwitterException, IllegalStateException {
		
		Query query = new Query(MENTION_TAG + stringToSearch);
	    QueryResult result = getTwitterInstance().search(query);
	    
	    return result.getTweets();
	}
		
	private Twitter getTwitterInstance() {
		return twitterInstance;
	}
}
