package org.zr.service.card;

import org.zr.entity.CardInfo;
import org.zr.entity.card;

public interface CardService {
/****΢�ſ��� service 
 * 1 �������� �ҳ�����Ϣ
 * 2 ����һ�ſ�������
 * 3 ���߿�����ȡ�ÿ�������Ϣ
 * 4 ����id ��ѯ����Ϣ
 * 
 * 
 * ***/
	CardInfo  getCardInformation(String type);
	void addCardInformation(CardInfo cai);
	card getCardByCode(String code);
	CardInfo getCardInformationByID(String id);
	
}
