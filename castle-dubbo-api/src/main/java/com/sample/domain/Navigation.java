package com.sample.domain;

public class Navigation extends Domain<Long> {

	private static final long serialVersionUID = -2283446651080177723L;

	private String name;

	private String position;

	private String url;

	private boolean blankTarget;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isBlankTarget() {
		return blankTarget;
	}

	public void setBlankTarget(boolean blankTarget) {
		this.blankTarget = blankTarget;
	}

}
