package org.zr.others;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.WeatherBean;
import org.zr.service.CreateMessage;
import org.zr.service.FaceService;
import org.zr.util.WeixinConstants;

public class GetWheather {
	private static Logger log= LoggerFactory.getLogger(GetWheather.class);
//取得网上的天气数据，进行查询
	//F9af0fcc4b18993fc7a6559683068b31 百度key  
	/***天气预报将回复 一个页面 显示天气 
	 * 
	 * 微信消息的话 只回复天气与温度
	 * **/
	
	
	
	public static JSONObject  Wherther1(String city){
		
		JSONObject jsobject=new JSONObject();
		String url="http://api.map.baidu.com/telematics/v3/weather?location=cityss&output=json&ak=F9af0fcc4b18993fc7a6559683068b31";
		try {
			url = url.replace("cityss", java.net.URLEncoder.encode(city, "UTF-8"));
			log.info("天气请求:"+url);
			String json = FaceService.httpRequest(url);
			 jsobject = JSONObject.fromObject(json);
			 return jsobject;
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			
			log.info("获取天气预报失败"+sw.toString());
			e.printStackTrace();
		}
		return jsobject;
	} 
	
	/**传入 jsonobj 返回wb**/
	public static  WeatherBean  Wherther2(JSONObject jsobject){
		WeatherBean web=new WeatherBean();
		String status=jsobject.getString("status");
		if(status.equals("success")){
			JSONArray jay=jsobject.getJSONArray("results");
			JSONObject jObject=jay.getJSONObject(0);
			String city1=jObject.getString("currentCity");
	        String pm25=jObject.getString("pm25");
			JSONArray jayindex=jObject.getJSONArray("index");
			
			JSONArray jaywdata=jObject.getJSONArray("weather_data");
			String wdata=jaywdata.getJSONObject(0).getString("date");
			wdata=wdata+","+jaywdata.getJSONObject(0).getString("weather");
			wdata=wdata+","+jaywdata.getJSONObject(0).getString("wind");
			wdata=wdata+","+jaywdata.getJSONObject(0).getString("temperature");
			String picurl=jaywdata.getJSONObject(0).getString("dayPictureUrl");
			web.setCity(city1);
			web.setPm25(pm25);
			web.setPic(picurl);
		
			web.setWeatherdate(wdata);
			
		}
		return web;
	}
	
	
	//创建天气查询的图文消息
	public static String createWheatherMessage(String toUserName,String fromUserName,String city) {
		WeatherBean wb= Wherther2( Wherther1(city));
		String title="今日"+city+"天气";
		String 	desc=wb.getWeatherdate()+",pm2.5指数"+wb.getPm25();
		String picUrl = wb.getPic();
		try {
			city =  java.net.URLEncoder.encode(city, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.debug("天气获取失败");
			e.printStackTrace();
		}
		String linkurl=WeixinConstants.WeatherUrl+"?city="+city;
		String result = "";
		result=CreateMessage.CreateArticleMessage(title, desc, picUrl, linkurl, toUserName, fromUserName);
		
		return result;
		

	}
	
	
	
	public static void main(String[] args) {
	
		System.out.println(createWheatherMessage("aa","bb","北京"));
		
	}
}
