package com.awesome.contents.episode.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awesome.contents.episode.vo.Season;
import com.awesome.contents.vo.Content;

@Service("episodeService")
public class EpisodeService {
	
	@Value("${api.services.url}")
	private String serviceUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public EpisodeService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<Content> getContentsByTitle(String title) {
		return Arrays.asList(restTemplate.getForObject(
				String.format("%s/v1/contents/search?title=%s", serviceUrl, title),
				Content[].class));
	}

	public Content getContentsDetail(String id) {
		return restTemplate.getForObject(String.format("%s/v1/contents/%s", serviceUrl, id),
				Content.class);
	}
	
	public List<Season> getAllEpisodes(String content) {
		return Arrays.asList(restTemplate.getForObject(
				String.format("%s/v1/contents/%s/episodes", serviceUrl, content), Season[].class));
	}

}
