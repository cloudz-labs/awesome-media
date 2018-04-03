package com.awesome.contents.episode.vo;

import java.util.List;

public class Season {
	
	private int season;
	
	private List<Episode> episodes;

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	@Override
	public String toString() {
		return "Season [season=" + season + ", episodes=" + episodes + "]";
	}
	
}
