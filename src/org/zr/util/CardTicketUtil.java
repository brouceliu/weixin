package org.zr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.card.WxCardSign;
import org.zr.entity.AccessToken;
import org.zr.entity.CardInfo;
import org.zr.entity.CardTicket;
import org.zr.entity.JsTicket;
import org.zr.entity.card;
import org.zr.model.dao.CardInfoDao;
import org.zr.model.dao.CardInfoDaoImpl;
import org.zr.model.dao.CardNumDao;
import org.zr.model.dao.CardNumDaoImol;
import org.zr.model.dao.CardTicketDao;
import org.zr.model.dao.CardTicketDaoImpl;
import org.zr.model.dao.TicketDao;
import org.zr.model.dao.TicketDaoimpl;
import org.zr.service.token.TokenService;

public class CardTicketUtil {
/****取得cardticket 类 ***/
	private static Logger log = LoggerFactory.getLogger(CardTicketUtil.class);
	
	/**通过get请求获得 ticket***/
	/**向服务器请求jsticket**/
	private static CardTicket getTicket(){
		AccessToken token=new AccessToken();
		token=TokenService.getAccesstoken();
		String toke=token.getToken();
		
		CardTicket ticket=new CardTicket();
		String url=WeixinConstants.CardTicketUrl;
		url=url.replace("ACCESS_TOKEN", toke);
		JSONObject jsonObject = TokenUtil.httpRequest(url, "GET", null);
		if(null !=jsonObject){
			try{
			ticket.setTicket(jsonObject.getString("ticket"));
			ticket.setExptime(jsonObject.getInt("expires_in"));
			Date date = new Date(); 
			SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			date=TimeUtil.addOneSecond(date, jsonObject.getInt("expires_in")-200);
			/**设置最后生效时间**/
			log.info("end time is "+from.format(date));
			ticket.setEndtime(from.format(date));
			CardTicketDao tk=new CardTicketDaoImpl();
			tk.addCardTicket(ticket);//存储进数据库
			}catch(Exception e){
				log.warn("获取cardticket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
			
		}
		return ticket;
	}
	
	/**从数据库查询ticket ，对比有效时间  ，
	 * 签名 = ticket+时间戳+随机串+sh1加密 
	 * ***/
	public static CardTicket getTicketFromMysql(){
		CardTicketDao tk=new CardTicketDaoImpl();
		//JsTicket  jst=new JsTicket();
        CardTicket ticket=tk.findTicket();
		if(null==ticket.getTicket()||ticket.getTicket().equals("")){
			log.info("*****cardticket is null*****");
			ticket=getTicket();
			
		}else{
			log.info(ticket.getEndtime()+ticket.getExptime());
			String time=ticket.getEndtime();
			Boolean result=TimeUtil.checksTime(time);
			if(result==false){
				log.info("*****cardticket timeout*****retry");
				ticket=getTicket();
			}
		}
		return ticket;
	}
	
	//生成签名  + 卡卷ticket +卡卷类型 返回
	
public static String GetSignAndTicket(String did,String cardid){
	String result="";
	//随机字符
	String suiji=JsTicketUtil.getRandomString(10);
	String time=TimeUtil.createTimeStept();//时间戳
	
	CardTicket cardticket=getTicketFromMysql();
	String ticket=cardticket.getTicket();//ticket
	
	CardNumDao cnd=new CardNumDaoImol();
	card ca=cnd.getCardfrom(cardid);
	String code=ca.getCardcode();
	String passwd=ca.getCardpasswd();
	String ALLCODE=code+passwd;
	log.info("code number is"+ALLCODE+"####");
	//cnd.updateCard(ca);
	
	CardInfoDao cdao=new CardInfoDaoImpl();
	CardInfo cinf=cdao.getCardInfoById(did);
	String id=cinf.getCardid();
	String type=cinf.getCardtype();
	
	
	log.info("***********开始生产card ticket***********");
	log.info(suiji);
	log.info(time);
	log.info(ticket);
	log.info(id);
	log.info(type);
	WxCardSign signer = new WxCardSign();
    signer.AddData(suiji);
    signer.AddData(time);
    signer.AddData(ticket);
    signer.AddData(id);
    signer.AddData(ALLCODE);
   //  signer.AddData(type);
   //  signer.AddData(WeixinConstants.appid);
    log.info("加密前面****"+signer.GetSignssss());
   // signer.AddData("SHA1");
    String sign=signer.GetSignature();//生成sign
    log.info(sign);
	result=suiji+","+time+","+id+","+type+","+sign+","+ALLCODE;
	log.info("***********完毕card ticket***********"+result);
	return result;
} 

//生产cardext 有 cardid appid  timestamp noncestr  code码  
/**2个ticket 不是一致的****/

public static String getAddTicket(){
	/*CardNumDao cnd=new CardNumDaoImol();
	card ca=cnd.getCardfrom();
	String code=ca.getCardcode();
	String passwd=ca.getCardpasswd();
	
	CardInfoDao cdao=new CardInfoDaoImpl();
	CardInfo cinf=cdao.getCardInfo("DISCOUNT");//DISCOUNT  CASH
	String id=cinf.getCardid();
	String type=cinf.getCardtype();
	
	//String codeAll=code+"passwd:"+passwd;
	
	//随机字符
		String suiji=JsTicketUtil.getRandomString(10);
		String time=TimeUtil.createTimeStept();//时间戳
		
		WxCardSign signer = new WxCardSign();
	    signer.AddData(suiji);
	    signer.AddData(time);
	  // signer.AddData(ticket);
	    signer.AddData(id);
	    signer.AddData(type);
	    signer.AddData(WeixinConstants.appid);
	    log.info("加密前面****"+signer.GetSignssss());
	   // signer.AddData("SHA1");
	    String sign=signer.GetSignature();//生成sign
*/	
	return null;
}


}
