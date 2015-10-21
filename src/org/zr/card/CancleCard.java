package org.zr.card;

import net.sf.json.JSONObject;

import org.zr.entity.AccessToken;
import org.zr.service.token.TokenService;
import org.zr.util.TokenUtil;
import org.zr.util.WeixinConstants;

public class CancleCard {
//作为卡卷作废 输入卡号 和 卡id
	
	
	public static String cardCancle(String cardid,String cardnum){
		String result="";
		String url=WeixinConstants.Canclecard;
		
		AccessToken token=new AccessToken();
		token=TokenService.getAccesstoken();
		String toke=token.getToken(); 
		
		url.replace("TOKEN", toke);
		
		JSONObject jsobj=new JSONObject();
		jsobj.put("code", cardnum);
		jsobj.put("card_id", cardid);

	    JSONObject jsonObject = TokenUtil.httpRequest(url, "POST", jsobj.toString());
		if(jsonObject.get("errmsg").equals("ok")){
			result="ok";
		}else{
			result="error";
		}
	    
		return result;
	} 
	
	
	
}
