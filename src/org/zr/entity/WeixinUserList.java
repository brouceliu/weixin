package org.zr.entity;

import java.util.List;

public class WeixinUserList {
/**΢���û��б�**/
	//��ע����
	private int tatle;
	//��ȡ��openid ����
		private int count;
		//openid �б�
	    private List<String> openIdList;
	    //��ȡ�б��һ���û�id
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
