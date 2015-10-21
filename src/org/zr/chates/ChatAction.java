package org.zr.chates;

import java.util.List;

import org.zr.entity.Knowledge;
import org.zr.model.dao.ChatDao;
import org.zr.model.dao.ChatDaoImpl;

public class ChatAction {

	// ȡ��Ц��
	public static String getJoker() {
		String result = "";
		ChatDao chad = new ChatDaoImpl();
		result = chad.getJoke();

		return result;

	}

	// ȡ���ϴ��������
	public static int getLastCategory(String openid) {

		ChatDao chad = new ChatDaoImpl();
		int result = chad.getLastCategory(openid);

		return result;

	}

	//��֪ʶ�ʴ�ֱ����ȡ��һ��
	
	public static String  getAnswerBySub(int knoledgeId) {

		ChatDao chad = new ChatDaoImpl();
		String result = chad.getSubknowledge(knoledgeId);

		return result;

	}
	
	//�洢������־
	
	public static void  Save(String openid,String createTime,String reqMeg,String respMeg,int chatCategry) {

		ChatDao chad = new ChatDaoImpl();
		 chad.saveChatLog(openid, createTime, reqMeg, respMeg, chatCategry);

		

	}
	
	
	public static List<Knowledge> getAllKnowledge(){
		ChatDao chad = new ChatDaoImpl();
		List<Knowledge> a=	chad.findAllKnowledge();
		return a;
	}
	//��ӿͷ���Ϣ
	
	public static void saveChatInfo(String question,String answer){
		ChatDao chad = new ChatDaoImpl();
	    chad.saveChatInfo(question, answer);
		
	}
}
