package com.awesome.api.contents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.api.contents.service.ContentsService;
import com.awesome.contents.vo.Content;

@RestController
@RequestMapping("/v1")
public class ContentsController {

	private ContentsService contentsService;
	
	@Autowired
	public ContentsController(ContentsService contentsService) {
		this.contentsService = contentsService;
	}
	
	@RequestMapping(path="/contents", method = RequestMethod.GET, name="getContents")
	public List<Content> getContents(@RequestParam(value = "category") String category) {
		return contentsService.getContentsByCategory(category);
	}

}