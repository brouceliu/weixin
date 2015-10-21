package org.zr.entity;

public class CardTicket {
//卡卷ticket  exptime 是tx给的时间  endtime 指的是有效期截止时间
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
