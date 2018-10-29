package com.awesome.api.contents.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awesome.contents.vo.Content;

@Service("contentsService")
public class ContentsService {

	@Value("${api.services.url}")
	private String serviceUrl;
	
	private RestTemplate restTemplate;

	@Autowired
	public ContentsService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Content> getContentsByCategory(String category) {
		return Arrays.asList(restTemplate.getForObject(
				String.format("%s/v1/contents?category=%s", serviceUrl, category),
				Content[].class));
	}

}