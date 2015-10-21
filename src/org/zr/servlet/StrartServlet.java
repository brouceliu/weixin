package org.zr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.token.TokenThread;
import org.zr.util.WeixinConstants;
import org.zr.util.WeixinUtil;

/**
 * ��ʼ��servlet
 * 
 * @author liuyq
 * @date 2013-05-02
 */
public class StrartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	public void init() throws ServletException {
		
		// ��ȡweb.xml�����õĲ���
		//TokenThread.appid = getInitParameter("appid");
		//TokenThread.appsecret = getInitParameter("appsecret");
		log.info("**********������ʼ���߳� init**************");
		log.info("weixin api appid:{}", WeixinConstants.appid);
		log.info("weixin api appsecret:{}", WeixinConstants.appSecret);
		// δ����appid��appsecretʱ������ʾ
		/*if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
			log.error("appid and appsecret configuration error, please check carefully.");
		} else {*/
		// ������ʱ��ȡaccess_token���߳�
		Thread s =	new Thread(new TokenThread());
		s.start();
		//}
	}
}