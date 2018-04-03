package com.awesome.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.category.service.CategoryService;
import com.awesome.category.vo.Category;

@RestController
@RequestMapping("/v1")
public class CategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(path="/categories", method = RequestMethod.GET, name="getCategories")
	public List<Category> getCategories() {
		List<Category> categories = categoryService.getCategoryList();
		return categories;
	}
	
	@RequestMapping(path="/categories/{id}", method=RequestMethod.GET, name="getCategory")
	public Category getCategory(@PathVariable(value = "id") String id) {
		Category category = categoryService.getCategory(id);
		return category;
	}

}
