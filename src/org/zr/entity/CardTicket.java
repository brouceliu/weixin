package org.zr.entity;

public class CardTicket {
//����ticket  exptime ��tx����ʱ��  endtime ָ������Ч�ڽ�ֹʱ��
	private String ticket; 
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public String getEndtime() {
		return endtime;
	}
	public int getExptime() {
		return exptime;
	}
	public void setExptime(int exptime) {
		this.exptime = exptime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	private int exptime;
	private String endtime;
	
}
