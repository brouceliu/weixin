package org.zr.model.dao;

import java.util.List;

import org.zr.entity.Knowledge;

public interface ChatDao {
  //获取问答库所有记录
	List<Knowledge> findAllKnowledge();
	//获取上次聊天类别
	int getLastCategory(String openid);
	//根据id 随机获取一个答案
	String getSubknowledge(int knoledgeId);
	//随机获取一个笑话
	String getJoke();
	//保存聊天记录
	void saveChatLog(String openid,String createTime,String reqMeg,String respMeg,int chatCategry);
	//保存导入的客服消息到聊天数据库
	void saveChatInfo(String question ,String answer);
	
	
	
}
