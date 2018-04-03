package com.awesome.api.contents.episode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.api.contents.episode.service.EpisodeService;
import com.awesome.api.contents.episode.vo.Episode;
import com.awesome.api.contents.episode.vo.Season;
import com.awesome.api.contents.vo.Content;

@RestController
@RequestMapping("/v1")
public class EpisodeRestController {
	
	private static Logger logger = LoggerFactory.getLogger(EpisodeRestController.class);
	
	private EpisodeService episodeService;
	
	@Autowired
	public EpisodeRestController(EpisodeService episodeService) {
		this.episodeService = episodeService;
	}

	@RequestMapping(path="/contents/search", method=RequestMethod.GET, name="searchContents")
	public List<Content> searchContents(@RequestParam(value = "title") String title) {
		logger.debug("searchContents() called!!");
		return episodeService.searchContents(title);
	}

	@RequestMapping(path = "/contents/{id}", method = RequestMethod.GET, name = "getContentsDetail")
	public Content getContentsDetail(@PathVariable(value = "id") String id) {
		logger.debug("getContentsDetail() called!!");
		return episodeService.getContentsDetail(id);
	}
	
	@RequestMapping(path="/contents/{content}/episodes", method=RequestMethod.GET, name="getAllEpisodes")
	public List<Season> getAllEpisodes(@PathVariable(value = "content") String content) {
		logger.debug("getContentsEpisodes() called!!");
		return episodeService.getAllEpisodes(content);
	}
	
	@RequestMapping(path="/contents/{content}/{season}", method=RequestMethod.GET)
	public List<Episode> getSeason(@PathVariable(value = "content") String content, @PathVariable(value = "season") int season) {
		logger.debug("getSeason() called!!");
		return episodeService.getSeason(content, season);
	}

	@RequestMapping(path="/contents/{content}/{season}/{episode}", method=RequestMethod.GET, name="getEpisodeDetail")
	public Episode getEpisodeDetail(@PathVariable(value = "content") String content, @PathVariable(value = "season") int season, @PathVariable(value = "episode") int episode) {
		logger.debug("getEpisodeDetail() called!!");
		return episodeService.getEpisodeDetail(content, season, episode);
	}

}
