package com.awesome.promotion.vo;

public class Promotion {
	
	private String category;
	
	private String content;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Notification [category:"+ category + ", content:"+ content +"]";
	}
}
