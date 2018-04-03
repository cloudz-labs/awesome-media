package com.awesome.api.contents.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.awesome.api.contents.vo.Content;

@Mapper
public interface ContentsMapper {
	
	@Select("select * from contents where category=#{category}")
	@Results({
        @Result(property = "hasEpisodes", column = "has_episodes"),
        @Result(property = "regDate", column = "reg_date")
    })
    public List<Content> findByCategory(String category);
	
	@Insert("insert into contents(category, title, grade, poster, stillcut, rate, year, summary, video, runtime, has_episodes, view, reg_date) values (#{category}, #{title}, #{grade}, #{poster}, #{stillcut}, #{rate}, #{year}, #{summary}, #{video}, #{runtime}, #{hasEpisodes}, #{view}, #{regDate})")
	public int insertContent(Content content); 

}
