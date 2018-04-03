package com.awesome.api.contents.episode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.awesome.api.contents.episode.vo.Episode;
import com.awesome.api.contents.vo.Content;

@Mapper
public interface EpisodeMapper {
	
	@Select("select * from contents where title like concat('%',#{title},'%')")
	@Results({
        @Result(property = "hasEpisodes", column = "has_episodes"),
        @Result(property = "regDate", column = "reg_date")
    })
	public List<Content> findByTitle(String title);
 	
	@Select("select * from contents where id=#{id}")
	@Results({
        @Result(property = "hasEpisodes", column = "has_episodes"),
        @Result(property = "regDate", column = "reg_date")
    })
	public Content findById(String id);

	@Select("select * from episodes where content=#{content}")
	public List<Episode> findByContent(String content);
	
	@Select("select distinct season from episodes where content=#{content}")
	public List<Integer> findSeasonsByContent(String content);
	
	@Select("select * from episodes where content=#{content} and season=#{season}")
	public List<Episode> findByContentAndSeason(@Param("content") String content, @Param("season") int season);
	
	@Select("select * from episodes where content=#{content} and season=#{season} and episode=#{episode}")
	public Episode findOne(@Param("content") String content, @Param("season") int season, @Param("episode") int episode);
	
}
