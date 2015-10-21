package org.zr.service.card;

import org.zr.entity.CardInfo;
import org.zr.entity.card;
import org.zr.model.dao.CardInfoDao;
import org.zr.model.dao.CardInfoDaoImpl;
import org.zr.model.dao.CardNumDao;
import org.zr.model.dao.CardNumDaoImol;

public class CardServiceImpl implements CardService {

	@Override
	public CardInfo getCardInformation(String type) {
		// 
		CardInfoDao cdao=new CardInfoDaoImpl();
		CardInfo  cinfo= cdao.getCardInfo(type);
		return cinfo;
	}

	public CardInfo getCardInformationByID(String id) {
		// 
		CardInfoDao cdao=new CardInfoDaoImpl();
		CardInfo  cinfo= cdao.getCardInfoById(id);
		return cinfo;
	}
	
	@Override
	public void addCardInformation(CardInfo cai) {
		CardInfoDao cdao=new CardInfoDaoImpl();
		cdao.addCardInfo(cai);
	}

	@Override
	public card getCardByCode(String code) {
		CardNumDao cdao=new CardNumDaoImol();
		card ca=cdao.getCardByCode(code);
		return ca;
	}

}
