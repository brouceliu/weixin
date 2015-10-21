package org.zr.entity;

public class UserInfo {
/**用户基本信息类**/
	
	private String openId;
	private int subscribe;//0 关注 1没关注
	private String subcribeTime;
	private String nickname;
	private int sex; //1 male 2 female
	private String country;
	private String province;
	private String city;
	private String language;
	private String headImageUrl;
	
	
	public String getOpenId() {
	return openId;
}
public void setOpenId(String openId) {
	this.openId = openId;
}
public int getSubscribe() {
	return subscribe;
}
public void setSubscribe(int subscribe) {
	this.subscribe = subscribe;
}
public String getSubcribeTime() {
	return subcribeTime;
}
public void setSubcribeTime(String subcribeTime) {
	this.subcribeTime = subcribeTime;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public int getSex() {
	return sex;
}
public void setSex(int sex) {
	this.sex = sex;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getHeadImageUrl() {
	return headImageUrl;
}
public void setHeadImageUrl(String headImageUrl) {
	this.headImageUrl = headImageUrl;
}
	
	
}
