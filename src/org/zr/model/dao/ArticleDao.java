package org.zr.model.dao;

import java.util.List;

import org.message.zr.response.Article;

public interface ArticleDao {
/****��ͼ����Ϣ���д洢 �޸� ɾ�� ��ѯ****/
	void savePicMeg(Article atcle,String mid);
	List<Article> findByMid(String mid);
	List<Article> findAll();
	void upDatePicMeg(String id,Article art,String mid);
}
