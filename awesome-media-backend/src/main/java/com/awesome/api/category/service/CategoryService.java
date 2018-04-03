package com.awesome.api.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awesome.api.category.dao.CategoryMapper;
import com.awesome.api.category.vo.Category;

@Service("categoryService")
public class CategoryService {
	
	private CategoryMapper categoryMapper;
	
	@Autowired
	public CategoryService(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public List<Category> getCategories() {
		return categoryMapper.findAll();
	}
	
	public Category getCategory(String id) {
		return categoryMapper.findById(id);
	}
	
}
