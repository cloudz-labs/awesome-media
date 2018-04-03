package com.skcc.promotion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skcc.contents.vo.Content;

@Service("promotionService")
public class PromotionService {
	
	@Value("${api.services.url}")
	private String serviceUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public PromotionService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Content getPromotion() {
		return restTemplate.getForObject(String.format("%s/v1/promotions", serviceUrl), Content.class);
    }
	
	public Content getPromotionFallBack() {
		return new Content();
    }
}
