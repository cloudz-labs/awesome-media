package com.skcc.category.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skcc.category.vo.Category;

@Service("categoryService")
public class CategoryService {
	
	private RestTemplate restTemplate;
	
	@Autowired
	public CategoryService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Value("${api.services.url}")
	private String serviceUrl;
	
	public List<Category> getCategoryList() {
		return Arrays.asList(restTemplate.getForObject(String.format("%s/v1/categories", serviceUrl), Category[].class));
    }
		
	public Category getCategory(String id) {
		return restTemplate.getForObject(String.format("%s/v1/categories/%s", serviceUrl, id), Category.class);
    }
	
}
