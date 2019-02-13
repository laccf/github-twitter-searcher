package com.sportdec.application.connectionProperty;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.sportdec.application.exception.MissingPropertyException;

public class TwitterProperties {

	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;

	private static final String CONSUMER_KEY = "consumer.key";
	private static final String CONSUMER_SECRET = "consumer.secret";
	private static final String ACCESS_TOKEN = "access.token";
	private static final String ACCESS_TOKEN_SECRET = "access.token.secret";

	public TwitterProperties(ResourceBundle bundle) throws MissingPropertyException {

		this.consumerKey = getProperty(bundle, CONSUMER_KEY);
		this.consumerSecret = getProperty(bundle, CONSUMER_SECRET);
		this.accessToken = getProperty(bundle, ACCESS_TOKEN);
		this.accessTokenSecret = getProperty(bundle, ACCESS_TOKEN_SECRET);

	}

	private String getProperty(ResourceBundle bundle, String property) throws MissingPropertyException {
		try {
			return bundle.getString(property);
		} catch (MissingResourceException e) {
			String error = String.format("Property %s is missing in properties file.", property);
			throw new MissingPropertyException(error);
		}
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

}
