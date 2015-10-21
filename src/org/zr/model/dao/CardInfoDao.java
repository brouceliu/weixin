package org.zr.model.dao;

import org.zr.entity.CardInfo;

public interface CardInfoDao {
/**
 * 1 存储卡卷 id sku 类型
 * 2 按照卡卷类型 取出卡卷 id sku 类型
 * 3 按照卡卷的id取出卡卷
 * ***/
	
	void addCardInfo(CardInfo ci);
	CardInfo getCardInfo(String type);
	CardInfo getCardInfoById(String id);
}
