package org.zr.model.dao;

import java.util.List;

public interface ArticleMapDao {

	/***��ѯ�û���Ϣ��Ӧ�Ļظ�  ����ͼ����Ϣ**/
	String findByContent(String content);
	void saveArticleMap(String mid,String content);
	List<String> findAllMegMap();
	void UpdateByid(String id ,String mid,String context);
}
