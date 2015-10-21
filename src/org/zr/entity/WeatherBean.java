package org.zr.entity;

public class WeatherBean {
//天气预报类
	private String pic;
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	private String city;
	private String pm25;
	private String clothes;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public String getClothes() {
		return clothes;
	}
	public void setClothes(String clothes) {
		this.clothes = clothes;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
		
	}
	public String getSports() {
		return sports;
	}
	public void setSports(String sports) {
		this.sports = sports;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
	public String getWeatherdate() {
		return weatherdate;
	}
	public void setWeatherdate(String weatherdate) {
		this.weatherdate = weatherdate;
	}
	private String ganmao;
	private String sports;
	private String uv;//紫外线
	private String weatherdate;//今天温度，风力
	
}
