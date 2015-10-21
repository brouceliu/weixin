package org.zr.model.dao;

import java.util.List;

import org.message.zr.request.BaseMessage;
import org.message.zr.request.TextMessage;

public interface MessageDao {
/**微信消息处理类 主要用来处理微信消息
 * 1 在数据库增加微信消息
 * 2 根据messageid 查询微信消息 去除重复的消息
 * 3 删除5天以前的微信消息，防止服务器问题
 * 4 按照unix时间搜索 固定时间段内的消息
 * **/
	
	String insertMessage(BaseMessage base);
	List<BaseMessage> selectByMid(String mid);
	String deleteMessage(String time);
	String  insertTextMessage(TextMessage text,String username);
	List<TextMessage> selectText();
	List<TextMessage> selectTextByTime(String timeStart,String timeEnd);
}
