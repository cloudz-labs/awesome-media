package com.awesome.api.promotion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.awesome.api.contents.episode.dao.EpisodeMapper;
import com.awesome.api.contents.vo.Content;
import com.awesome.api.promotion.dao.PromotionMapper;
import com.awesome.api.promotion.vo.Promotion;

@Service("promotionsService")
public class PromotionsService {
	
	@Value("${promotion}")
	private String promotion;
	
	private EpisodeMapper episodeMapper;
	
	private PromotionMapper promotionMapper;
	
	@Autowired
	public PromotionsService(EpisodeMapper episodeMapper, PromotionMapper promotionMapper) {
		this.episodeMapper = episodeMapper;
		this.promotionMapper = promotionMapper;
	}
	
	public Content getPromotion() {
		String categoryId = promotion;		
		Promotion promotion = promotionMapper.findByCategoryId(categoryId);
		if (promotion != null) {
			return episodeMapper.findById(promotion.getContent());
		} else {
			return null;
		}
	}
	
}
