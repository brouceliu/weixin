package org.zr.token;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.AccessToken;
import org.zr.entity.JsTicket;
import org.zr.util.JsTicketUtil;
import org.zr.util.TokenUtil;
import org.zr.util.WeixinConstants;
import org.zr.util.WeixinUtil;

/**
 * 定时获取微信access_token的线程
 * 
 * @author liuyq
 * @date 2013-05-02
 */
public class TokenThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	// 第三方用户唯一凭证
	
	public static String appid =WeixinConstants.appid;
	// 第三方用户唯一凭证密钥
	public static String appsecret = WeixinConstants.appSecret;
	public static AccessToken accessToken = null;
	public static JsTicket jsticket = null;
	public void run() {
		synchronized (TokenThread.class) {
		while (true) {
			try {
			
				accessToken = TokenUtil.getAccessToken(appid, appsecret);
				
				if (null != accessToken) {
					log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());
					// 休眠7000秒
					log.info("*******************sleep time is "+(accessToken.getExpiresIn() - 200) * 1000);
					
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					// 如果access_token为null，300秒后再获取
					log.warn("token 没有取得到，300s后再次获取");
					Thread.sleep(300 * 1000);
				}
				
			} catch (InterruptedException e) {
				StringWriter sw = new StringWriter();   
	            PrintWriter pw = new PrintWriter(sw, true);   
	            e.printStackTrace(pw);   
	            pw.flush();   
	            sw.flush();     
				log.warn("取得token时候错误>>>>>>"+sw.toString());
				e.printStackTrace();
				try {
					log.warn("**********300s后再次获取token"
							+ "**********");
					Thread.sleep(300 * 1000);
				} catch (InterruptedException e1) {
					log.error("{}", e1);
				}
				log.error("{}", e);
			}
			
		}
		}
	}
}