package com.awesome.profile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.profile.service.ProfileService;
import com.awesome.profile.vo.Profile;

@RestController
@RequestMapping("/v1")
public class ProfileController {
	
	private ProfileService profileService;
	
	@Autowired
	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}
 
	@RequestMapping(path="/profiles", method=RequestMethod.GET, name="getProfiles")
	public List<Profile> getProfiles(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		List<Profile> profiles = profileService.getProfiles(username);
		return profiles;
	}
}
