package org.zr.model.dao;

import org.zr.entity.JsTicket;

public interface TicketDao {
/***��ȡjsticket ���ݲ���
 * ����ticket
 * ȡ��ticket
 * 
 * **/
	void addTicket(JsTicket tick);
	JsTicket getTicket();
	
}
