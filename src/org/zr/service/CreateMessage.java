package org.zr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;





import org.message.zr.response.Article;
import org.message.zr.response.NewsMessage;
import org.message.zr.response.TextMessage;
import org.zr.entity.KeFuMEssage;
import org.zr.util.MessageUtil;

public class CreateMessage {
	/** 创建一个消息类 用来做图文 消息 纯文本消息 **/

	//单图文消息
	public static String CreateArticleMessage(String title, String description,
			String picUrl, String url, String toUserName, String fromUserName) {
		// 创建图文消息
		Article article = new Article();
		article.setTitle(title);
		article.setDescription(description);
		article.setPicUrl(picUrl);
		article.setUrl(url);
		List<Article> articleList = new ArrayList<Article>();
		articleList.add(article);
		// 图文消息创建
		NewsMessage newsMessage = new NewsMessage();
		
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		String rsp = MessageUtil.newsMessageToXml(newsMessage);
		return rsp;
	}

	//创建一条图文消息
	public static Article CreateOneArticle(String title, String description,
			String picUrl, String url){
		// 创建图文消息
				Article article = new Article();
				article.setTitle(title);
				article.setDescription(description);
				article.setPicUrl(picUrl);
				article.setUrl(url);
		return article;
	}
	//组装图文消息
	public static  String BuildArticle(String toUserName,String fromUserName, List<Article> articleList){
		// 图文消息创建
				NewsMessage newsMessage = new NewsMessage();
				
				newsMessage.setToUserName(toUserName);
				newsMessage.setFromUserName(fromUserName);
				
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setArticleCount(articleList.size());
				newsMessage.setArticles(articleList);
				String rsp = MessageUtil.newsMessageToXml(newsMessage);
				return rsp;
		
	}
	
	//创建文本消息
	public static String CreateTextMessage(String fromUserName,String toUserName,String text){
		// 回复文本消息
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					
					textMessage.setContent(text);
					String result=MessageUtil.textMessageToXml(textMessage);
					return result;
	}
	
	//创建多客服消息
		public static String CreateCustomMessage(String fromUserName,String toUserName){
			// 回复文本消息
						KeFuMEssage kf = new KeFuMEssage();
						kf.setToUserName(fromUserName);
						kf.setFromUserName(toUserName);
						kf.setCreateTime(new Date().getTime());
						kf.setMsgType(MessageUtil.CUSTOMER_SERVICE);
						String result=MessageUtil.kfMessageToXml(kf);
						return result;
		}
		
	
}
