package org.zr.model.dao;

import org.zr.entity.CardInfo;

public interface CardInfoDao {
/**
 * 1 �洢���� id sku ����
 * 2 ���տ������� ȡ������ id sku ����
 * 3 ���տ����idȡ������
 * ***/
	
	void addCardInfo(CardInfo ci);
	CardInfo getCardInfo(String type);
	CardInfo getCardInfoById(String id);
}
