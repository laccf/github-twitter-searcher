package com.sportdec.application.service;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.testng.annotations.Test;

import com.sportdec.application.connectionProperty.TwitterProperties;
import com.sportdec.application.exception.MissingPropertyException;

import twitter4j.TwitterException;

public class TwitterServiceTest {

	private TwitterService tService;
	private TwitterProperties props;
	
	@Test(expectedExceptions = IllegalStateException.class)
	public void shouldThrowIllegalStateException() throws TwitterException {
		
		props = mock(TwitterProperties.class);
		tService = new TwitterService(props);
		tService.searchTweet("Football");
	}
	
	@Test(expectedExceptions = MissingPropertyException.class)
	public void shouldThrowMissingPropertyException() throws TwitterException, MissingPropertyException {
		TwitterProperties props = new TwitterProperties(mock(ResourceBundle.class));
		tService = new TwitterService(props);
		tService.searchTweet("Football");
	}
	
	@Test(expectedExceptions = IOException.class)
	public void shouldThrowTwitterException() throws TwitterException, MissingPropertyException, IOException {
		ResourceBundle bundle = new  PropertyResourceBundle(Files.newInputStream(Paths.get("fileDoesntExist")));
		TwitterProperties props = new TwitterProperties(bundle);
		tService = new TwitterService(props);
		tService.searchTweet("Football");
	}
	

}
