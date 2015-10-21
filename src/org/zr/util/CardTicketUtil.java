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
/****ȡ��cardticket �� ***/
	private static Logger log = LoggerFactory.getLogger(CardTicketUtil.class);
	
	/**ͨ��get������ ticket***/
	/**�����������jsticket**/
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
			/**���������Чʱ��**/
			log.info("end time is "+from.format(date));
			ticket.setEndtime(from.format(date));
			CardTicketDao tk=new CardTicketDaoImpl();
			tk.addCardTicket(ticket);//�洢�����ݿ�
			}catch(Exception e){
				log.warn("��ȡcardticketʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
			
		}
		return ticket;
	}
	
	/**�����ݿ��ѯticket ���Ա���Чʱ��  ��
	 * ǩ�� = ticket+ʱ���+�����+sh1���� 
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
	
	//����ǩ��  + ����ticket +�������� ����
	
public static String GetSignAndTicket(String did,String cardid){
	String result="";
	//����ַ�
	String suiji=JsTicketUtil.getRandomString(10);
	String time=TimeUtil.createTimeStept();//ʱ���
	
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
	
	
	log.info("***********��ʼ����card ticket***********");
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
    log.info("����ǰ��****"+signer.GetSignssss());
   // signer.AddData("SHA1");
    String sign=signer.GetSignature();//����sign
    log.info(sign);
	result=suiji+","+time+","+id+","+type+","+sign+","+ALLCODE;
	log.info("***********���card ticket***********"+result);
	return result;
} 

//����cardext �� cardid appid  timestamp noncestr  code��  
/**2��ticket ����һ�µ�****/

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
	
	//����ַ�
		String suiji=JsTicketUtil.getRandomString(10);
		String time=TimeUtil.createTimeStept();//ʱ���
		
		WxCardSign signer = new WxCardSign();
	    signer.AddData(suiji);
	    signer.AddData(time);
	  // signer.AddData(ticket);
	    signer.AddData(id);
	    signer.AddData(type);
	    signer.AddData(WeixinConstants.appid);
	    log.info("����ǰ��****"+signer.GetSignssss());
	   // signer.AddData("SHA1");
	    String sign=signer.GetSignature();//����sign
*/	
	return null;
}


}
