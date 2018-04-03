package com.awesome.api.promotion.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.awesome.api.promotion.vo.Promotion;

@Mapper
public interface PromotionMapper {
	
	@Select("select * from promotions where category=#{category}")
	public Promotion findByCategoryId(String categoryId);
}
