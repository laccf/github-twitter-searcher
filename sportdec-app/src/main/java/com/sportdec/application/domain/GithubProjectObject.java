package com.sportdec.application.domain;

public class GithubProjectObject {

	private long id;
	private String name;
	private String description;
	
	public long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		if(description == null) {
			description = "[no description]";
		}
		return description;
	}
	
	public String toString() {
		return String.format("id: {%s}, name: {%s}, description: {%s}", id, name, description);
	}
	
	
}
