package com.sportdec.application.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.sportdec.application.domain.GithubSearchObject;

public class GithubService {

	private static final String GIT_URL = "https://api.github.com/search/repositories";
	private static final String UTF_8 = "UTF-8";
	
	public GithubSearchObject searchRepositories(String wordToSearch, String order, String sort) throws ParseException, IOException{
		
		String query = "?q=" + wordToSearch;
		
		if(isValid(order)) {
			query += "&order=" + order;
		}
		if(isValid(sort)) {
			query += "&sort=" + sort;
		}
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(GIT_URL + query);
		System.out.println("Requested URL: <" + GIT_URL + query + ">");
		HttpResponse result = httpClient.execute(request);
		String json = EntityUtils.toString(result.getEntity(), UTF_8);

		Gson gson = new Gson();
		GithubSearchObject githubProject = gson.fromJson(json, GithubSearchObject.class);
		
		return githubProject;

	}
	
	private boolean isValid(String stringToValidate) {
		return stringToValidate != null && !stringToValidate.isEmpty();
	}
}
