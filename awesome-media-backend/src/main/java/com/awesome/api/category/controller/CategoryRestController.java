package com.awesome.api.category.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.api.category.service.CategoryService;
import com.awesome.api.category.vo.Category;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryRestController.class);
	
	private CategoryService categoryService;
	
	@Autowired
	public CategoryRestController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(path="/categories", method=RequestMethod.GET, name="getCategories")
	public List<Category> getCategories() throws Exception {
		logger.debug("getCategories() called!!");
		return categoryService.getCategories();
	}
	
	@RequestMapping(path="/categories/{id}", method=RequestMethod.GET, name="getCategory")
	public Category getCategory(@PathVariable(value = "id") String id) throws Exception {
		logger.debug("getCategory() called!!");
		return categoryService.getCategory(id);
	}
}