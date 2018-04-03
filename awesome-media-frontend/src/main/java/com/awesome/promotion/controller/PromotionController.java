package com.awesome.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.contents.vo.Content;
import com.awesome.promotion.service.PromotionService;

@RestController
@RequestMapping("/v1")
public class PromotionController {
	
	private PromotionService promotionService;
	
	@Autowired
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
 
	@RequestMapping(path="/promotions", method=RequestMethod.GET, name="getPromotion")
	public Content getPromotions() {
		Content promotion = promotionService.getPromotion();
		return promotion;
	}
}
