package org.zr.model.dao;

import java.util.List;

import org.message.zr.response.Article;

public interface ArticleDao {
/****对图文消息进行存储 修改 删除 查询****/
	void savePicMeg(Article atcle,String mid);
	List<Article> findByMid(String mid);
	List<Article> findAll();
	void upDatePicMeg(String id,Article art,String mid);
}
