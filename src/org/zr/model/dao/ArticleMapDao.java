package org.zr.model.dao;

import java.util.List;

public interface ArticleMapDao {

	/***查询用户消息对应的回复  保存图文消息**/
	String findByContent(String content);
	void saveArticleMap(String mid,String content);
	List<String> findAllMegMap();
	void UpdateByid(String id ,String mid,String context);
}
