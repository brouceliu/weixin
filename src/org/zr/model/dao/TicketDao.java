package org.zr.model.dao;

import org.zr.entity.JsTicket;

public interface TicketDao {
/***获取jsticket 数据操作
 * 增加ticket
 * 取出ticket
 * 
 * **/
	void addTicket(JsTicket tick);
	JsTicket getTicket();
	
}
