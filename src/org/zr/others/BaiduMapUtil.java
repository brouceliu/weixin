package org.zr.others;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.message.zr.response.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.BaiduPlace;
import org.zr.entity.UserLocation;
import org.zr.model.dao.UserLocationDaoJdbc;
import org.zr.service.FaceService;
import org.zr.util.Base64Util;

import com.mongodb.util.Base64Codec;

//F9af0fcc4b18993fc7a6559683068b31

public class BaiduMapUtil {
	private static Logger log = LoggerFactory.getLogger(BaiduMapUtil.class);
public static List<BaiduPlace> searchPlace(String query,String lat,String lng){
	List<BaiduPlace> places=new ArrayList<BaiduPlace>();
	String url="http://api.map.baidu.com/place/v2/search?&query=QUERY&location=LAT,LNG&radius=2000&output=xml&page_size=10&page_num=0&ak=F9af0fcc4b18993fc7a6559683068b31";
	try {
		url=url.replace("QUERY", URLEncoder.encode(query, "utf-8"));
		url=url.replace("LAT", lat);
		url=url.replace("LNG", lng);
		log.info("向百度发请求"+url);
		String respon=FaceService.httpRequest(url);
		places=prasePlaceXml(respon,lat,lng);
	} catch (Exception e) {
		StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        e.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
       
		//log.info("插入数据库错误"+sw.toString());
		log.info("百度请求搜索错误"+sw.toString());
		e.printStackTrace();
	}
	
	return places;
}


/**解析百度返回的信息**/
private static List<BaiduPlace> prasePlaceXml(String xml,String lat,String lon){
	List<BaiduPlace> placelist=null;
	try {
		Document document= DocumentHelper.parseText(xml);
		Element root=document.getRootElement();
		Element resultele=root.element("results");
		List<Element> resultEleList=resultele.elements("result");
		if(resultEleList.size()>0){
			placelist=new ArrayList<BaiduPlace>();
			Element nameElement=null;
			Element addressElement=null;
			Element locaElement=null; 
			Element telephoneElement=null;
			Element detailinfoElement=null;
			Element distanceElement=null;
			for(Element resultElement : resultEleList){
				nameElement=resultElement.element("name");
				addressElement=resultElement.element("address");
				locaElement = resultElement.element("location");
				telephoneElement =resultElement.element("telephone");
				detailinfoElement = resultele.element("detail_info");
				
				BaiduPlace place=new BaiduPlace();
				place.setName(nameElement.getText());
				place.setAddress(addressElement.getText());
				place.setLat(locaElement.element("lat").getText());
				place.setLng(locaElement.element("lng").getText());
				if(null !=telephoneElement){
					place.setTelephone(telephoneElement.getText());
					
				}
				if(null !=detailinfoElement){
					distanceElement=detailinfoElement.element("distance");
					if(null !=distanceElement){
						place.setDistance(Integer.parseInt(distanceElement.getText()));
					}
				}else{
					place.setDistance((int)LantitudeLongitudeDist(Double.parseDouble(lat), Double.parseDouble(lon), Double.parseDouble(locaElement.element("lat").getText()), Double.parseDouble(locaElement.element("lng").getText())));
				}
				
				placelist.add(place);
				Collections.sort(placelist);
			}
			//CA21bdecc75efc1664af5a195c30bb4e
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return placelist;
	
}

/**根据位置组装图文消息**/
public static List<Article> makeArticleList(List<BaiduPlace> placelist,String bdLng,String bdLat){
	String basepath="http://119.79.233.21/Txwx/";//项目根路径
	List<Article> list=new ArrayList<Article>();
	BaiduPlace place=null;
	for(int i=0;i<placelist.size();i++){
		place=placelist.get(i);
		Article article=new Article();
		article.setTitle(place.getName()+"\n距离约"+place.getDistance()+"米");
		article.setUrl(String.format(basepath+"map.jsp?p1=%s,%s&p2=%s,%s",bdLng,bdLat,place.getLng(),place.getLat()));
		//p1 表示用户发送的位置 p2表示当前poi所在位置
		if(i==0){
			article.setPicUrl(basepath+"images/nice1.jpg");
		}else{
			article.setPicUrl(basepath+"images/nice1.jpg");
			list.add(article);
		}
		
	}
	return list;
}

/**将微信定位的坐标转换成百度坐标**/
public static UserLocation ConvertCoord(String lng,String lat){
	String converturl="http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x={x}&y={y}";

	
	converturl=converturl.replace("{x}", lng);
	converturl=converturl.replace("{y}", lat);
	UserLocation location=new UserLocation();
	try{
	String jsonCoord=FaceService.httpRequest(converturl);
	JSONObject jso=JSONObject.fromObject(jsonCoord);
	//对转化后的坐标做base64解码
	location.setBd09Lng(Base64Util.decodeStr(jso.getString("x")));
	location.setBd09Lat(Base64Util.decodeStr(jso.getString("y")));
	log.info(Base64Util.decodeStr(jso.getString("x"))+"!!!!"+Base64Util.decodeStr(jso.getString("y")));
	}catch(Exception e){
		location=null;
		StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        e.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
       
		//log.info("插入数据库错误"+sw.toString());
		log.info("坐标转换错误"+sw.toString());
		e.printStackTrace();
	}
	return location;
}
private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)
/**
 * 转化为弧度(rad)
 * */
private static double rad(double d)
{
   return d * Math.PI / 180.0;
}

/**
 * 基于余弦定理求两经纬度距离
 * @param lon1 第一点的精度
 * @param lat1 第一点的纬度
 * @param lon2 第二点的精度
 * @param lat3 第二点的纬度
 * @return 返回的距离，单位km
 * */
public static double LantitudeLongitudeDist(double lon1, double lat1,double lon2, double lat2) {
	double radLat1 = rad(lat1);
	double radLat2 = rad(lat2);

	double radLon1 = rad(lon1);
	double radLon2 = rad(lon2);

	if (radLat1 < 0)
		radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
	if (radLat1 > 0)
		radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
	if (radLon1 < 0)
		radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
	if (radLat2 < 0)
		radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
	if (radLat2 > 0)
		radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
	if (radLon2 < 0)
		radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
	double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
	double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
	double z1 = EARTH_RADIUS * Math.cos(radLat1);

	double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
	double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
	double z2 = EARTH_RADIUS * Math.cos(radLat2);

	double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
	//余弦定理求夹角
	double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
	double dist = theta * EARTH_RADIUS;
	return dist;
}
}

