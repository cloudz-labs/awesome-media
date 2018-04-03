package com.awesome.api.profile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.awesome.api.profile.vo.Profile;

@Mapper
public interface ProfileMapper {
	
	@Select("select * from profiles where username=#{username}")
    public List<Profile> findByUsername(String username);

}
