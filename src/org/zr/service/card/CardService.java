package org.zr.service.card;

import org.zr.entity.CardInfo;
import org.zr.entity.card;

public interface CardService {
/****微信卡卷 service 
 * 1 更具类型 找出卡信息
 * 2 增加一张卡卷类型
 * 3 更具卡号码取得卡所有信息
 * 4 更具id 查询卡信息
 * 
 * 
 * ***/
	CardInfo  getCardInformation(String type);
	void addCardInformation(CardInfo cai);
	card getCardByCode(String code);
	CardInfo getCardInformationByID(String id);
	
}
