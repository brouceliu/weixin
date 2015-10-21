package org.zr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.token.TokenThread;
import org.zr.util.WeixinConstants;
import org.zr.util.WeixinUtil;

/**
 * 初始化servlet
 * 
 * @author liuyq
 * @date 2013-05-02
 */
public class StrartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	public void init() throws ServletException {
		
		// 获取web.xml中配置的参数
		//TokenThread.appid = getInitParameter("appid");
		//TokenThread.appsecret = getInitParameter("appsecret");
		log.info("**********启动初始化线程 init**************");
		log.info("weixin api appid:{}", WeixinConstants.appid);
		log.info("weixin api appsecret:{}", WeixinConstants.appSecret);
		// 未配置appid、appsecret时给出提示
		/*if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
			log.error("appid and appsecret configuration error, please check carefully.");
		} else {*/
		// 启动定时获取access_token的线程
		Thread s =	new Thread(new TokenThread());
		s.start();
		//}
	}
}