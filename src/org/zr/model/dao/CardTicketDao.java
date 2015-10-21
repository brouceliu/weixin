package org.zr.model.dao;

import org.zr.entity.CardTicket;

public interface CardTicketDao {

	/***1 插入微信劵 api ticket
	 * 2 查找微信劵ticket
	 * ***/
	void addCardTicket(CardTicket cardticket);
	CardTicket findTicket();
}
