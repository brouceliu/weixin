package org.zr.card;

import net.sf.json.JSONObject;

import org.zr.entity.AccessToken;
import org.zr.entity.CardInfo;
import org.zr.entity.card;
import org.zr.model.dao.CardInfoDao;
import org.zr.model.dao.CardInfoDaoImpl;
import org.zr.model.dao.CardNumDao;
import org.zr.model.dao.CardNumDaoImol;
import org.zr.service.token.TokenService;
import org.zr.util.TokenUtil;
import org.zr.util.WeixinConstants;

public class DestroyCard {
/***ºËÏú¿¨¾í***/
	
	public static boolean DestroyCards(String code,String passwd){
		boolean result=false;
		AccessToken token=new AccessToken();
		token=TokenService.getAccesstoken();
		String toke=token.getToken(); 
		
		String url=WeixinConstants.Destroycard;
		url=url.replace("TOKEN", toke);
		CardNumDao  cnd=new CardNumDaoImol();
		card cs=cnd.getCardBypasswd(code, passwd);
		
		CardInfoDao d= new CardInfoDaoImpl();
		CardInfo cif=d.getCardInfo(cs.getCardtype());
		String id=cif.getCardid();
		
		JSONObject jso=new JSONObject();
		jso.put("code", code+passwd);
		jso.put("card_id", id);
		
		JSONObject jsonObject = TokenUtil.httpRequest(url, "POST", jso.toString());
		if(jsonObject.get("errmsg").equals("ok")){
			result=true;
		}
		return result;
	}
	
}
