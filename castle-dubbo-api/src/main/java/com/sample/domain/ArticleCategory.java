package com.sample.domain;

public class ArticleCategory extends Domain<Long> {

	private static final long serialVersionUID = -413576259023421179L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
