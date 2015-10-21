package org.zr.model.dao;

import java.util.List;

import org.zr.entity.Knowledge;

public interface ChatDao {
  //��ȡ�ʴ�����м�¼
	List<Knowledge> findAllKnowledge();
	//��ȡ�ϴ��������
	int getLastCategory(String openid);
	//����id �����ȡһ����
	String getSubknowledge(int knoledgeId);
	//�����ȡһ��Ц��
	String getJoke();
	//���������¼
	void saveChatLog(String openid,String createTime,String reqMeg,String respMeg,int chatCategry);
	//���浼��Ŀͷ���Ϣ���������ݿ�
	void saveChatInfo(String question ,String answer);
	
	
	
}
