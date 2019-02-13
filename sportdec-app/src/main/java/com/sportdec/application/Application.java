package com.sportdec.application;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.http.ParseException;

import com.sportdec.application.connectionProperty.TwitterProperties;
import com.sportdec.application.domain.GithubProjectObject;
import com.sportdec.application.domain.GithubSearchObject;
import com.sportdec.application.domain.SearchParametersObject;
import com.sportdec.application.exception.FileNotFoundExpception;
import com.sportdec.application.exception.MissingArgumentException;
import com.sportdec.application.exception.MissingPropertyException;
import com.sportdec.application.exception.TwitterAPIException;
import com.sportdec.application.io.FileReader;
import com.sportdec.application.service.GithubService;
import com.sportdec.application.service.TwitterService;
import com.sportdec.application.view.Menu;
import com.sportdec.application.view.ViewPrinterIO;

import twitter4j.Status;
import twitter4j.TwitterException;

public class Application {

	private static final String WORD_ARGUMENT = "w";
	private static final String ORDER_ARGUMENT = "o";
	private static final String SORT_ARGUMENT = "s";

	static GithubService ghService = new GithubService();
	static Menu menu = new Menu();
	static ViewPrinterIO viewPrinter = new ViewPrinterIO();
	static TwitterService twtService;

	public static void main(String[] args) {

		Options menuOptions = menu.getOptions();
		loadPropertiesAndValidateArgs(menuOptions, args);
	}

	private static void loadPropertiesAndValidateArgs(Options menuOptions, String... args) {

		try {
			loadProperties();
		} catch (MissingPropertyException | FileNotFoundExpception e) {
			viewPrinter.printError("Before to proceed you must set up the properties file.");
			viewPrinter.printError(e.getMessage());
			return;
		}

		try {
			
			SearchParametersObject objToSearch = validateArgsAndReturnParameters(menuOptions, args);
			doSearchAndPrintResults(objToSearch);
			
		} catch (TwitterAPIException e) {
			viewPrinter.printError(e.getMessage());

		} catch(MissingArgumentException e) {
			viewPrinter.printError(e.getMessage());
			viewPrinter.printHelpMenu(menu);
		}
		catch (Exception ex) {
			viewPrinter.printError("If you inform a command you must inform the referenced arg.\n");
			viewPrinter.printHelpMenu(menu);
		}

	}

	private static SearchParametersObject validateArgsAndReturnParameters(Options menuOptions, String... args) throws TwitterAPIException, Exception {
		
		CommandLineParser parser = new DefaultParser();

		CommandLine cmd = parser.parse(menuOptions, args, false);

		if (cmd.hasOption(WORD_ARGUMENT)) {

			String word = getArgument(cmd, WORD_ARGUMENT);

			if (isWordValidArgument(word)) {

				String order = getArgument(cmd, ORDER_ARGUMENT);
				String sort = getArgument(cmd, SORT_ARGUMENT);

				SearchParametersObject obj = new SearchParametersObject(word, order, sort);
				
				return obj;

			} else {
				throw new MissingArgumentException("You must inform -w command and argument.");
			}

		} else {
			throw new MissingArgumentException("You must inform -w command and argument.");
		}

	}

	private static void loadProperties() throws MissingPropertyException, FileNotFoundExpception {
		ResourceBundle bundle = FileReader.readFileProperties();
		TwitterProperties properties = new TwitterProperties(bundle);
		twtService = new TwitterService(properties);
	}

	private static void doSearchAndPrintResults(SearchParametersObject obj)
			throws TwitterAPIException, ParseException, IOException {
		GithubSearchObject ghSearchObj = ghService.searchRepositories(obj.getWord(), obj.getOrder(), obj.getSort());
		List<GithubProjectObject> projObjects = ghSearchObj.getItems();

		viewPrinter.printGithubProjects(projObjects);
		List<Long> idsLong = viewPrinter.getGithubIds();

		List<GithubProjectObject> filteredList = projObjects.stream()
				.filter(project -> idsLong.contains(project.getId())).collect(Collectors.toList());

		System.out.println("\nList of tweets:");
		filteredList.forEach(project -> {

			try {
				List<Status> tweets = twtService.searchTweet(project.getName());
				viewPrinter.printTweets(project.getName(), tweets);

			} catch(IllegalStateException e) {
				throw new TwitterAPIException(
						"It was not possible to request tweet. Authentication credentials are missing.");
			} catch (TwitterException e) {
				throw new TwitterAPIException(
						"It was not possible to request tweet. Http code status: " + e.getStatusCode());
			}

		});
	}

	private static String getArgument(CommandLine cmd, String arg) {
		return cmd.hasOption(arg) ? cmd.getOptionValue(arg) : "";
	}

	private static boolean isWordValidArgument(String word) {
		return word != null && !word.isEmpty();
	}
}
