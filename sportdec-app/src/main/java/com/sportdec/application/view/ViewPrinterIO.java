package com.sportdec.application.view;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.HelpFormatter;

import com.sportdec.application.domain.GithubProjectObject;

import twitter4j.Status;

public class ViewPrinterIO {

	
	public void printGithubProjects(List<GithubProjectObject> projObjects) {
		System.out.println("\nList of github projects:\n");
		projObjects.forEach(project -> {
			String projDesc = String.format("id: %d\nproject name: %s\ndescription: %s" , project.getId(), project.getName(), project.getDescription());
			System.out.println(projDesc);
			System.out.println("--------------------------------");
		});
	}
	
	public void printError(String errorMessage) {
		System.out.println("Error: " + errorMessage);
	}
	
	public void printTweets(String projectName, List<Status> tweets ) {
		
		if (tweets.isEmpty() || tweets.size() == 0) {
			System.out.println(String.format("For project <%s> no tweets were found.", projectName));
		} else {
			tweets.forEach(tweet -> {
				printTweet(projectName, tweet);
			});
		}
		
		System.out.println("***************************************************");
	}
	
	
	private void printTweet(String projectName, Status tweet ) {
		
		String tweeted = String.format("project_name: %s\ntweet_id: %d\ncreated_at: %s\ntext:%s", 
				projectName, tweet.getId(), tweet.getCreatedAt(), tweet.getText());

		System.out.println(tweeted);
		System.out.println("---------------------------------------------------");
	}
	
	@SuppressWarnings("resource")
	public List<Long> getGithubIds() {
		
		System.out.println("List the ids you want to search the tweets:");
		Scanner scanner = new Scanner(System.in);
		String scannerInput = scanner.nextLine();
		
		String[] ids = scannerInput.split(" ");
		List<Long> idsLong = new ArrayList<Long>();
		for (String string : ids) {
			try {
				Long id = Long.valueOf(string);		
				if(!idsLong.contains(id)) {
					idsLong.add(id);
				}
				
			} catch (Exception ex) {
				//avoid it
				continue;
			}
		}
		
		return idsLong;
	}
	
	public void printHelpMenu(Menu menu) {
		HelpFormatter formatter = new HelpFormatter();
		PrintWriter writer = new PrintWriter(System.out);
		formatter.printUsage(writer, 80, "sportdec-app.jar", menu.getOptions());

		formatter.printOptions(writer, 80, menu.getOptions(), 2 ,2);
		writer.flush();
	}
	
}
