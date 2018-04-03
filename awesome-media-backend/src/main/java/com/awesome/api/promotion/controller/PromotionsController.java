package com.awesome.api.promotion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.api.contents.vo.Content;
import com.awesome.api.promotion.service.PromotionsService;

@RestController
@RequestMapping("/v1")
public class PromotionsController {
	
	private static Logger logger = LoggerFactory.getLogger(PromotionsController.class);

	private PromotionsService promotionsService;
	
	@Autowired
	public PromotionsController(PromotionsService promotionsService) {
		this.promotionsService = promotionsService;
	}
	
	@RequestMapping(path="/promotions", method=RequestMethod.GET, name="getPromotions")
	public Content getPromotions() throws Exception {
		logger.debug("getPromotions() called!!");
		return promotionsService.getPromotion();
	}
}
