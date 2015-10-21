package org.zr.customservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.util.WeixinUtil;

import net.sf.json.JSONObject;

public class SendCusMessage {
/**���Ϳͷ���Ϣ**/
	private static Logger log=LoggerFactory.getLogger(SendCusMessage.class);
	/***���Ϳͷ���Ϣ**/
	public static boolean sendCustomSerMessage(String accesstoken,String jsonMsg){
		boolean result=false;
		//ƴ�ӵ�ַ
		String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";	
		url=url.replace("ACCESS_TOKEN", accesstoken);
		//���Ϳͷ���Ϣ
		log.info("�ͷ���Ϣ���͵�ַ"+url);
		JSONObject jsobj=WeixinUtil.httpRequest(url, "POST", jsonMsg);
		if(null !=jsobj){
			int errcode=jsobj.getInt("errcode");
			String errMsg=jsobj.getString("errmsg");
			if(0==errcode){
				result=true;
				log.info("�ͷ�����Ϣ���ͳɹ�");
			}else{
				log.info("�ͷ�����Ϣ����ʧ��");
			}
		}
		return result;
	}
}
