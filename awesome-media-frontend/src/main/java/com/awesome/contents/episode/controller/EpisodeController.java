package com.awesome.contents.episode.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.contents.episode.service.EpisodeService;
import com.awesome.contents.episode.vo.Season;
import com.awesome.contents.service.ContentsService;
import com.awesome.contents.vo.Content;

@RestController
@RequestMapping("/v1")
public class EpisodeController {
	
	private ContentsService contentsService;
	
	private EpisodeService episodeService;
	
	@Autowired
	public EpisodeController(ContentsService contentsService, EpisodeService episodeService) {
		this.contentsService = contentsService;
		this.episodeService = episodeService;
	}
	
	@RequestMapping(path="/contents/search", method=RequestMethod.GET, name="searchContents")
	public List<Content> searchContents(@RequestParam(value = "title") String title) {
		return episodeService.getContentsByTitle(title);
	}
	
	@RequestMapping(path = "/contents/{id}", method = RequestMethod.GET, name="getContentsDetail")
	public Content getContentsDetail(@PathVariable(value = "id") String id) {
		return episodeService.getContentsDetail(id);
	}
	
	@RequestMapping(path="/contents/{id}/episodes", method=RequestMethod.GET, name="getAllEpisodes")
	public List<Season> getAllEpisodes(@PathVariable(value = "id") String id) {
		return episodeService.getAllEpisodes(id);
	}
	
	@RequestMapping(path="/contents/{id}/similars", method=RequestMethod.GET, name="getSimilars")
	public List<Content> getSimilars(@PathVariable(value = "id") String id, @RequestParam(value = "category") String category) {
		Content content = episodeService.getContentsDetail(id);
		List<Content> similars = new ArrayList<Content>(contentsService.getContentsByCategory(category));
		similars.remove(content);
		return similars;
	}

}
