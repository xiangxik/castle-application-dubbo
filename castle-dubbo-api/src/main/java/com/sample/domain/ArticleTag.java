package com.sample.domain;

public class ArticleTag extends Domain<Long> {

	private static final long serialVersionUID = 3698243328172307720L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
