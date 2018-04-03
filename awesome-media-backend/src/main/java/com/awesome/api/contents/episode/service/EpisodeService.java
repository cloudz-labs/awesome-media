package com.awesome.api.contents.episode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awesome.api.contents.episode.dao.EpisodeMapper;
import com.awesome.api.contents.episode.vo.Episode;
import com.awesome.api.contents.episode.vo.Season;
import com.awesome.api.contents.vo.Content;

@Service("episodeService")
public class EpisodeService {
	
	private EpisodeMapper episodeMapper;
	
	@Autowired
	public EpisodeService(EpisodeMapper episodeMapper) {
		this.episodeMapper = episodeMapper;
	}
	
	public List<Content> searchContents(String title) {
		return episodeMapper.findByTitle(title);
	}

	public Content getContentsDetail(String id) {
		return episodeMapper.findById(id);
	}

	public List<Season> getAllEpisodes(String content) {
		List<Season> allEpisodes = new ArrayList<Season>();
		List<Integer> seasons = episodeMapper.findSeasonsByContent(content);
		seasons.forEach(seasonIndex -> {
			Season season = new Season();
			season.setSeason(seasonIndex);
			season.setEpisodes(episodeMapper.findByContentAndSeason(content, seasonIndex));
			allEpisodes.add(season);
		});
		
		return allEpisodes;
	}
	
	public List<Episode> getSeason(String content, int season) {
		return episodeMapper.findByContentAndSeason(content, season);
	}
	
	public Episode getEpisodeDetail(String content, int season, int episode) {
		return episodeMapper.findOne(content, season, episode);
	}

}
