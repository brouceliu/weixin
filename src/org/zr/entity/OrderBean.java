package org.zr.entity;

public class OrderBean {

	private String paystatus;  //订单状态
	private String processsstatus;//订单流程状态
	private String sellername;//卖家名字
	private double allprice;//订单总价
	
	
	
	
	public String getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}
	public String getProcesssstatus() {
		return processsstatus;
	}
	public void setProcesssstatus(String processsstatus) {
		this.processsstatus = processsstatus;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public double getAllprice() {
		return allprice;
	}
	public void setAllprice(double allprice) {
		this.allprice = allprice;
	}
	
}
