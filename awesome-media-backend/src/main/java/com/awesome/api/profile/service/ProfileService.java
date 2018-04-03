package com.awesome.api.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awesome.api.profile.dao.ProfileMapper;
import com.awesome.api.profile.vo.Profile;

@Service("profileService")
public class ProfileService {
	
	private ProfileMapper profileMapper;
	
	@Autowired
	public ProfileService(ProfileMapper profileMapper) {
		this.profileMapper = profileMapper;
	}
	
	public List<Profile> getProfiles(String username) {
		return profileMapper.findByUsername(username);
	}
	
}
