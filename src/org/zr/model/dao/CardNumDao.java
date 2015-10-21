package org.zr.model.dao;

import org.zr.entity.card;

public interface CardNumDao {

	/***1 �洢card��num passwd status type
	 *  2 ����card��status
	 *  3 ȡ��card ��num passwd Ҫ�� cardstatus ==no û�ù�
	 *  4 ͨ������ĺ��� ȡ������type 
	 *  5 ͨ�������code ���ҿ��� ***/
	void addCard(card ca);
	void updateCard(card ca);
	card getCardfrom(String cardid);
	card getCardid(String type);
	
	card getCardBypasswd(String code ,String passwd);
	card getCardByCode(String code);
}
