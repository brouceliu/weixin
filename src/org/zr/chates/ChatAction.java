package org.zr.chates;

import java.util.List;

import org.zr.entity.Knowledge;
import org.zr.model.dao.ChatDao;
import org.zr.model.dao.ChatDaoImpl;

public class ChatAction {

	// 取得笑话
	public static String getJoker() {
		String result = "";
		ChatDao chad = new ChatDaoImpl();
		result = chad.getJoke();

		return result;

	}

	// 取得上次聊天类别
	public static int getLastCategory(String openid) {

		ChatDao chad = new ChatDaoImpl();
		int result = chad.getLastCategory(openid);

		return result;

	}

	//从知识问答分表随机取出一条
	
	public static String  getAnswerBySub(int knoledgeId) {

		ChatDao chad = new ChatDaoImpl();
		String result = chad.getSubknowledge(knoledgeId);

		return result;

	}
	
	//存储聊天日志
	
	public static void  Save(String openid,String createTime,String reqMeg,String respMeg,int chatCategry) {

		ChatDao chad = new ChatDaoImpl();
		 chad.saveChatLog(openid, createTime, reqMeg, respMeg, chatCategry);

		

	}
	
	
	public static List<Knowledge> getAllKnowledge(){
		ChatDao chad = new ChatDaoImpl();
		List<Knowledge> a=	chad.findAllKnowledge();
		return a;
	}
	//添加客服消息
	
	public static void saveChatInfo(String question,String answer){
		ChatDao chad = new ChatDaoImpl();
	    chad.saveChatInfo(question, answer);
		
	}
}
