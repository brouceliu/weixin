package org.zr.entity;

public class JsTicket {

	/**调用js api 需要的key 有三个参数  key 有效时间 key有效截至时间**/
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
