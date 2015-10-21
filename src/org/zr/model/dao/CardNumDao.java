package org.zr.model.dao;

import org.zr.entity.card;

public interface CardNumDao {

	/***1 存储card的num passwd status type
	 *  2 更新card的status
	 *  3 取出card 的num passwd 要求 cardstatus ==no 没用过
	 *  4 通过卡卷的号码 取出卡卷type 
	 *  5 通过卡卷的code 查找卡卷 ***/
	void addCard(card ca);
	void updateCard(card ca);
	card getCardfrom(String cardid);
	card getCardid(String type);
	
	card getCardBypasswd(String code ,String passwd);
	card getCardByCode(String code);
}
