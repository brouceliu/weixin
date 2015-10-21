package org.zr.entity;

import java.util.List;

public class WeixinUserList {
/**微信用户列表**/
	//关注总数
	private int tatle;
	//获取的openid 个数
		private int count;
		//openid 列表
	    private List<String> openIdList;
	    //拉取列表后一个用户id
	    private String nextOpenId;
	public int getTatle() {
	return tatle;
}
public void setTatle(int tatle) {
	this.tatle = tatle;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public List<String> getOpenIdList() {
	return openIdList;
}
public void setOpenIdList(List<String> openIdList) {
	this.openIdList = openIdList;
}
public String getNextOpenId() {
	return nextOpenId;
}
public void setNextOpenId(String nextOpenId) {
	this.nextOpenId = nextOpenId;
}
	
	
}
