package com.awesome.contents.vo;

public class Content {
	
	private int id;
	
	private String category;
	
	private String title;
	
	private double grade;
	
	private String poster;
	
	private String stillcut;
	
	private String rate;
	
	private int year;
	
	private String summary;
	
	private String video;
	
	private int runtime;
	
	private boolean hasEpisodes;
	
	private int view;
	
	private String regDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getStillcut() {
		return stillcut;
	}

	public void setStillcut(String stillcut) {
		this.stillcut = stillcut;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public boolean isHasEpisodes() {
		return hasEpisodes;
	}

	public void setHasEpisodes(boolean hasEpisodes) {
		this.hasEpisodes = hasEpisodes;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", category=" + category + ", title=" + title + ", grade=" + grade + ", poster="
				+ poster + ", stillcut=" + stillcut + ", rate=" + rate + ", year=" + year + ", summary=" + summary
				+ ", video=" + video + ", runtime=" + runtime + ", hasEpisodes=" + hasEpisodes + ", view=" + view
				+ ", regDate=" + regDate + "]";
	}

}
