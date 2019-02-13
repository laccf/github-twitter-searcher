package com.sportdec.application.domain;

public class SearchParametersObject {

	private String word;
	private String order;
	private String sort;
	
	public SearchParametersObject(String word, String order, String sort) {
		this.word = word;
		this.order = order;
		this.sort = sort;
	}
	
	public String getWord() {
		return word;
	}
	public String getOrder() {
		return order;
	}
	public String getSort() {
		return sort;
	}
	
}
