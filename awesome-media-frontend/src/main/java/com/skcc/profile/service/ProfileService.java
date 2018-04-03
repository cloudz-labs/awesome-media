package com.skcc.profile.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skcc.profile.vo.Profile;

@Service("profileService")
public class ProfileService {
	
	@Value("${api.services.url}")
	private String serviceUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public ProfileService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<Profile> getProfiles(String username) {
		return Arrays.asList(restTemplate.getForObject(String.format("%s/v1/%s/profiles", serviceUrl, username), Profile[].class));
    }
	
}
