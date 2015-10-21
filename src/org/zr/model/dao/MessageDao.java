package org.zr.model.dao;

import java.util.List;

import org.message.zr.request.BaseMessage;
import org.message.zr.request.TextMessage;

public interface MessageDao {
/**΢����Ϣ������ ��Ҫ��������΢����Ϣ
 * 1 �����ݿ�����΢����Ϣ
 * 2 ����messageid ��ѯ΢����Ϣ ȥ���ظ�����Ϣ
 * 3 ɾ��5����ǰ��΢����Ϣ����ֹ����������
 * 4 ����unixʱ������ �̶�ʱ����ڵ���Ϣ
 * **/
	
	String insertMessage(BaseMessage base);
	List<BaseMessage> selectByMid(String mid);
	String deleteMessage(String time);
	String  insertTextMessage(TextMessage text,String username);
	List<TextMessage> selectText();
	List<TextMessage> selectTextByTime(String timeStart,String timeEnd);
}
