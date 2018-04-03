package com.awesome.api.category.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.awesome.api.category.vo.Category;

@Mapper
public interface CategoryMapper {
	
	@Select("select * from categories")
	public List<Category> findAll();
	
	@Select("select * from categories where id=#{id}")
	public Category findById(String id);

}
