package org.zr.entity;

public class JsTicket {

	/**����js api ��Ҫ��key ����������  key ��Чʱ�� key��Ч����ʱ��**/
	private String jskey;
	private String endTime;
	private int expiresIn;
	public void setJskey(String jskey) {
		this.jskey = jskey;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getJskey() {
		return jskey;
	}
	public String getEndTime() {
		return endTime;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	
	
}
