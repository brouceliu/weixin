package org.zr.model.dao;

import org.zr.entity.CardTicket;

public interface CardTicketDao {

	/***1 ����΢�ń� api ticket
	 * 2 ����΢�ń�ticket
	 * ***/
	void addCardTicket(CardTicket cardticket);
	CardTicket findTicket();
}
