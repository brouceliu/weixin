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
	/** ����һ����Ϣ�� ������ͼ�� ��Ϣ ���ı���Ϣ **/

	//��ͼ����Ϣ
	public static String CreateArticleMessage(String title, String description,
			String picUrl, String url, String toUserName, String fromUserName) {
		// ����ͼ����Ϣ
		Article article = new Article();
		article.setTitle(title);
		article.setDescription(description);
		article.setPicUrl(picUrl);
		article.setUrl(url);
		List<Article> articleList = new ArrayList<Article>();
		articleList.add(article);
		// ͼ����Ϣ����
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

	//����һ��ͼ����Ϣ
	public static Article CreateOneArticle(String title, String description,
			String picUrl, String url){
		// ����ͼ����Ϣ
				Article article = new Article();
				article.setTitle(title);
				article.setDescription(description);
				article.setPicUrl(picUrl);
				article.setUrl(url);
		return article;
	}
	//��װͼ����Ϣ
	public static  String BuildArticle(String toUserName,String fromUserName, List<Article> articleList){
		// ͼ����Ϣ����
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
	
	//�����ı���Ϣ
	public static String CreateTextMessage(String fromUserName,String toUserName,String text){
		// �ظ��ı���Ϣ
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					
					textMessage.setContent(text);
					String result=MessageUtil.textMessageToXml(textMessage);
					return result;
	}
	
	//������ͷ���Ϣ
		public static String CreateCustomMessage(String fromUserName,String toUserName){
			// �ظ��ı���Ϣ
						KeFuMEssage kf = new KeFuMEssage();
						kf.setToUserName(fromUserName);
						kf.setFromUserName(toUserName);
						kf.setCreateTime(new Date().getTime());
						kf.setMsgType(MessageUtil.CUSTOMER_SERVICE);
						String result=MessageUtil.kfMessageToXml(kf);
						return result;
		}
		
	
}
