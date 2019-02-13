package com.sportdec.application.service;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.ParseException;
import org.testng.annotations.Test;

import com.sportdec.application.domain.GithubSearchObject;

public class GithubServiceTest {

	private GithubService gService;
	
	@Test
	public void shouldContainKeyword() throws ParseException, IOException {

		String keyWord = "Football";
		gService = new GithubService();
		GithubSearchObject githubProject = gService.searchRepositories(keyWord, "", "");
		
		githubProject.getItems().forEach( item -> {
			assertTrue(item.getName().toLowerCase().contains(keyWord.toLowerCase()) || item.getDescription().toLowerCase().contains(keyWord.toLowerCase()));
		});
	}
}
