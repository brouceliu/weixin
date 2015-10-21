package org.zr.main;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.AccessToken;
import org.zr.entity.UserInfo;
import org.zr.entity.WeixinUserList;
import org.zr.model.dao.AccessTokenImpl;
import org.zr.util.TokenUtil;
import org.zr.util.WeixinConstants;
import org.zr.util.WeixinUtil;

public class GetWeiXinUser {
	private static Logger log=LoggerFactory.getLogger(GetWeiXinUser.class);
/**获取微信用户信息**/
	public static UserInfo getWeinxinUser(String openid,AccessToken token){
		String url=WeixinConstants.WeixinUser_url;
		UserInfo us=null;
			url=url.replace("ACCESSTOKEN", token.getToken()).replace("OPENID", openid);
			JSONObject  json=WeixinUtil.httpRequest(url, "GET", null);
			if(null !=json){
				try{
					us=new UserInfo();	
					us.setSubscribe(json.getInt("subscribe"));
					us.setCity(json.getString("city"));
					us.setCountry(json.getString("country"));
					us.setNickname(json.getString("nickname"));
					us.setHeadImageUrl(json.getString("headimgurl"));
					us.setSex(json.getInt("sex"));
					us.setProvince(json.getString("province"));
					us.setSubcribeTime(json.getString("subscribe_time"));
				}catch (Exception e){
					if(0 == us.getSubscribe()){
						log.warn("此用户没有关注武商网");
						int  errorcode=json.getInt("errcode");
						String errmessage=json.getString("errmsg");
						log.warn("取得用户失败 "+errorcode);
						log.warn("失败信息 "+errmessage);
					}else{
						int  errorcode=json.getInt("errcode");
						String errmessage=json.getString("errmsg");
						log.warn("取得用户失败 "+errorcode);
						log.warn("失败信息 "+errmessage);
						
					}
				}
			}
			
			return us;
		
	}
	
	
	/**获取微信用户列表**/
	public static WeixinUserList getUserList(AccessToken token,String nextOpenid){
		WeixinUserList wxList=null;
		if(null == nextOpenid){
			nextOpenid="";
			}
			String url=WeixinConstants.WeixinUserList_url;
			url=url.replace("ACCESSTOKEN", token.getToken()).replace("NEXTOPENID", nextOpenid);
			log.info(url);
			JSONObject jsobj=WeixinUtil.httpRequest(url, "GET", null);
			if(null !=jsobj){
				try{
					wxList=new WeixinUserList();
					wxList.setTatle(jsobj.getInt("total"));
					wxList.setCount(jsobj.getInt("count"));
					wxList.setNextOpenId(jsobj.getString("next_openid"));
					JSONObject dataobj=(JSONObject) jsobj.get("data");
					wxList.setOpenIdList(JSONArray.toList(dataobj.getJSONArray("openid"),List.class));
					
				}catch(Exception e){
					wxList=null;
					int errcode=jsobj.getInt("errcode");
					String errMeg=jsobj.getString("errmsg");
					log.warn("获取关注列表失败"+errMeg+errcode);
				}
			}
		return wxList;
	}
	//owRFtt2cfLz2f5IkvsI7D8h4WzDg  owRFtt6HDaz7JHs0N9Iz4HE0BWJg
	
	public static void main(String[] args) {
		AccessToken at = TokenUtil.getAccessToken(WeixinConstants.appid, WeixinConstants.appSecret);
		/*WeixinUserList li=getUserList(at, null);
		System.out.println(li.getTatle());
		System.out.println(li.getCount());
		System.out.println(li.getOpenIdList().toString());
		System.out.println(li.getNextOpenId());*/
		UserInfo us=getWeinxinUser("owRFtt2cfLz2f5IkvsI7D8h4WzDg", at);
		System.out.println(us.getNickname());
		System.out.println(us.getCity());
		System.out.println(us.getSubcribeTime());
	}
	
	
}


