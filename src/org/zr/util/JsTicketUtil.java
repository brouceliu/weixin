package org.zr.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.AccessToken;
import org.zr.entity.JsTicket;
import org.zr.model.dao.AccessTokenImpl;
import org.zr.model.dao.TicketDao;
import org.zr.model.dao.TicketDaoimpl;
import org.zr.service.token.TokenService;

public class JsTicketUtil {
	private static Logger log = LoggerFactory.getLogger(JsTicketUtil.class);
/**��ȡjsticket ��ֵ ����Чʱ��
 *���Ȼ�ȡ token
 *ͨ��token ��ȡ jskey
 * ***/
	
	/**�����ݿ��ѯticket ���Ա���Чʱ��  ��***/
	public static JsTicket getTicketFromMysql(){
		TicketDao tk=new TicketDaoimpl();
	
		JsTicket jsticket=tk.getTicket();
		if(null==jsticket.getJskey()||jsticket.getJskey().equals("")){
			log.info("*****ticket is null*****");
			jsticket=getTicket();
			
		}else{
			log.info(jsticket.getEndTime()+jsticket.getExpiresIn());
			String time=jsticket.getEndTime();
			Boolean result=TimeUtil.checksTime(time);
			if(result==false){
				log.info("*****ticket timeout*****retry");
				jsticket=getTicket();
			}
		}
		return jsticket;
	}
	
	/**�����������jsticket**/
	public static JsTicket getTicket(){
		AccessToken token=new AccessToken();
		token=TokenService.getAccesstoken();
		String toke=token.getToken();
		
		JsTicket ticket=new JsTicket();
		String url=WeixinConstants.JSAPIURL;
		url=url.replace("ACCESS_TOKEN", toke);
		JSONObject jsonObject = TokenUtil.httpRequest(url, "GET", null);
		if(null !=jsonObject){
			try{
			ticket.setJskey(jsonObject.getString("ticket"));
			ticket.setExpiresIn(jsonObject.getInt("expires_in"));
			Date date = new Date(); 
			SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			date=TimeUtil.addOneSecond(date, jsonObject.getInt("expires_in")-200);
			/**���������Чʱ��**/
			log.info("end time is "+from.format(date));
			ticket.setEndTime(from.format(date));
			TicketDao tk=new TicketDaoimpl();
			tk.addTicket(ticket);//�洢�����ݿ�
			}catch(Exception e){
				log.warn("��ȡticketʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
			
		}
		return ticket;
	}
	
	
	
	
	
	/***��������ַ��� 16λ**/
	public static String getRandomString(int length) { //length��ʾ�����ַ����ĳ���
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   
	
	/**��ticket����ƴ�Ӽ�����***/
	public static String sha1Ticket(String okurl){
		//��ȡjsticket
		String jst=getTicketFromMysql().getJskey();
		//��ȡ����ַ���
		String noncestr=getRandomString(16);
		//��ȡʱ���
		String time=TimeUtil.createTimeStept();
		//��ȡ��Ҫ����jsjdk��url
		
		//1 ����ASCII ���������
		String key="jsapi_ticket="+jst+"&noncestr="+noncestr+"&timestamp="+time+"&url="+okurl;
		String result=DigestUtils.shaHex(key);
		return result+","+noncestr+","+time;
	}
	
	public static void main(String[] args) {
	//	System.out.println(getTicket().getJskey());
		String a="\\";
		String b="\\";
		System.out.println(a);
		System.out.println(b);
	}
	
}
