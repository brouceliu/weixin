package org.zr.service.picMessage;

import java.util.List;

import org.message.zr.response.Article;
import org.zr.model.dao.ArticleDao;
import org.zr.model.dao.ArticleDaoImpl;
import org.zr.model.dao.ArticleMapDao;
import org.zr.model.dao.ArticleMapDaoImpl;
import org.zr.service.CreateMessage;

public class ReplyPicMeg {
/**根据用户的语言回复图文消息 ***/
  public String replayMeg(String content,String toUserName,String fromUserName){
	  ArticleMapDao atmap=new ArticleMapDaoImpl();
	  String mid=atmap.findByContent(content);
	  ArticleDao article=new ArticleDaoImpl();
	  List<Article> list=article.findByMid(mid);
	  String result=CreateMessage.BuildArticle(toUserName, fromUserName, list);
	  return result;
	  
  }
	/***存储图文消息  mid  picurl url description title ***/
	public static Boolean findPicMeg(String context){
		Boolean a=true;
		ArticleMapDao atmap=new ArticleMapDaoImpl();
		  String mid=atmap.findByContent(context);
		  if(mid.equals("k")){
			  a=false;
		  }
		  return a;
	}
}
