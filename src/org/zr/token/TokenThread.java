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
 * ��ʱ��ȡ΢��access_token���߳�
 * 
 * @author liuyq
 * @date 2013-05-02
 */
public class TokenThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	// �������û�Ψһƾ֤
	
	public static String appid =WeixinConstants.appid;
	// �������û�Ψһƾ֤��Կ
	public static String appsecret = WeixinConstants.appSecret;
	public static AccessToken accessToken = null;
	public static JsTicket jsticket = null;
	public void run() {
		synchronized (TokenThread.class) {
		while (true) {
			try {
			
				accessToken = TokenUtil.getAccessToken(appid, appsecret);
				
				if (null != accessToken) {
					log.info("��ȡaccess_token�ɹ�����Чʱ��{}�� token:{}", accessToken.getExpiresIn(), accessToken.getToken());
					// ����7000��
					log.info("*******************sleep time is "+(accessToken.getExpiresIn() - 200) * 1000);
					
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					// ���access_tokenΪnull��300����ٻ�ȡ
					log.warn("token û��ȡ�õ���300s���ٴλ�ȡ");
					Thread.sleep(300 * 1000);
				}
				
			} catch (InterruptedException e) {
				StringWriter sw = new StringWriter();   
	            PrintWriter pw = new PrintWriter(sw, true);   
	            e.printStackTrace(pw);   
	            pw.flush();   
	            sw.flush();     
				log.warn("ȡ��tokenʱ�����>>>>>>"+sw.toString());
				e.printStackTrace();
				try {
					log.warn("**********300s���ٴλ�ȡtoken"
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