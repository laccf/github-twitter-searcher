package com.sportdec.application.view;

import org.apache.commons.cli.Options;

public class Menu {

	private Options options;
	
	public Menu() {
		this.options = generateMenu();
	}
	
	private Options generateMenu() {
		Options options = new Options();
		
		options.addOption("w", "word", true, 
				"Required. The word to search.");
		
		options.addOption("s", "sort", true, 
				"Sorts the results by number of [stars], [forks], or [help-wanted-issues] or how recently the items were [updated]. Default: [best match]");
		
		options.addOption("o", "order", true, 
				"Determines if the first search result returned is [desc] or [asc]. Default: [desc]");

		return options;
	}
	
	
	public Options getOptions() {
		return this.options;
	}
}
