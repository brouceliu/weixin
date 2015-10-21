package org.zr.entity;

public class KeFuMEssage {
/**客服消息**/
	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private String MsgType;
	public String getToUserName() {
	return ToUserName;
}
public void setToUserName(String toUserName) {
	ToUserName = toUserName;
}
public String getFromUserName() {
	return FromUserName;
}
public void setFromUserName(String fromUserName) {
	FromUserName = fromUserName;
}
public Long getCreateTime() {
	return CreateTime;
}
public void setCreateTime(Long createTime) {
	CreateTime = createTime;
}
public String getMsgType() {
	return MsgType;
}
public void setMsgType(String msgType) {
	MsgType = msgType;
}
	
}
