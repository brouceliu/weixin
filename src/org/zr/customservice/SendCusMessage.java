package org.zr.customservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.util.WeixinUtil;

import net.sf.json.JSONObject;

public class SendCusMessage {
/**发送客服消息**/
	private static Logger log=LoggerFactory.getLogger(SendCusMessage.class);
	/***发送客服消息**/
	public static boolean sendCustomSerMessage(String accesstoken,String jsonMsg){
		boolean result=false;
		//拼接地址
		String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";	
		url=url.replace("ACCESS_TOKEN", accesstoken);
		//发送客服消息
		log.info("客服消息发送地址"+url);
		JSONObject jsobj=WeixinUtil.httpRequest(url, "POST", jsonMsg);
		if(null !=jsobj){
			int errcode=jsobj.getInt("errcode");
			String errMsg=jsobj.getString("errmsg");
			if(0==errcode){
				result=true;
				log.info("客服的消息发送成功");
			}else{
				log.info("客服的消息发送失败");
			}
		}
		return result;
	}
}
