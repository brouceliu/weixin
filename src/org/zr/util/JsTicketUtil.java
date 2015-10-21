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
/**获取jsticket 的值 和有效时间
 *首先获取 token
 *通过token 获取 jskey
 * ***/
	
	/**从数据库查询ticket ，对比有效时间  ，***/
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
	
	/**向服务器请求jsticket**/
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
			/**设置最后生效时间**/
			log.info("end time is "+from.format(date));
			ticket.setEndTime(from.format(date));
			TicketDao tk=new TicketDaoimpl();
			tk.addTicket(ticket);//存储进数据库
			}catch(Exception e){
				log.warn("获取ticket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
			
		}
		return ticket;
	}
	
	
	
	
	
	/***生成随机字符串 16位**/
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   
	
	/**对ticket进行拼接及加密***/
	public static String sha1Ticket(String okurl){
		//获取jsticket
		String jst=getTicketFromMysql().getJskey();
		//获取随机字符串
		String noncestr=getRandomString(16);
		//获取时间戳
		String time=TimeUtil.createTimeStept();
		//获取需要引入jsjdk的url
		
		//1 按照ASCII 码进行排序
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
