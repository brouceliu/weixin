package org.zr.entity;

public class UserLocation {
//用于用户的地理位置信息
	
	/**lzr create 2015-3-31 **/
	private String openId; //用户opean id
	private String lng;
	private String lat;
	private String bd09Lng;
	private String bd09Lat;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getBd09Lng() {
		return bd09Lng;
	}
	public void setBd09Lng(String bd09Lng) {
		this.bd09Lng = bd09Lng;
	}
	public String getBd09Lat() {
		return bd09Lat;
	}
	public void setBd09Lat(String bd09Lat) {
		this.bd09Lat = bd09Lat;
	}
	
}
