package org.zr.card;

import net.sf.json.JSONObject;

import org.zr.entity.AccessToken;
import org.zr.entity.CardInfo;
import org.zr.service.token.TokenService;
import org.zr.util.TokenUtil;
import org.zr.util.WeixinConstants;

public class UpdateCard {
/***����card***/
	/***���޸� ʹ����ʾ ʹ�� ˵��
	 * ���� ֪ͨ�ַ�  ˵���ַ�  
	 * ��Ҫcard id card type
	 * 
	 * ***/
	public static String  update(String notice,String descript,CardInfo cai){
		String  result="";
		String id=cai.getCardid();
		String type=cai.getCardtype().toLowerCase();
		// �õ�token
		AccessToken token=new AccessToken();
		token=TokenService.getAccesstoken();
		String toke=token.getToken(); 
		//���json
		//��һ��
		JSONObject js=new JSONObject();
		js.put("card_id", id);
		//�ڶ���
		JSONObject js1=new JSONObject();
		//������
		JSONObject js2=new JSONObject();
		
		//���Ĳ�
		
		
		if(!descript.equals("")){
			js2.put("description", descript);
		}
		
		if(!notice.equals("")){
		js2.put("notice", notice);
		}
		
		
		js1.put("base_info", js2);
		
		js.put(type, js1);
		
        String url=WeixinConstants.Updatecard;
		url=url.replace("TOKEN", toke);
	    JSONObject jsonObject = TokenUtil.httpRequest(url, "POST", js.toString());
		
	    if(jsonObject.get("errmsg").equals("ok")){
	    	result="ok";
	    }else{
	    	result=jsonObject.get("errmsg").toString();
	    }
	    
		return result;
	}
	
}
