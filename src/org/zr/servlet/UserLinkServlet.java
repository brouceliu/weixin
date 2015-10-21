package org.zr.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.model.dao.WxLinkWsuserImpl;
import org.zr.util.WeixinConstants;

public class UserLinkServlet  extends HttpServlet{
private static Logger log=LoggerFactory.getLogger(UserLinkServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**绑定的时候还需要验证 是否是有效的武商网账号***/
		    req.setCharacterEncoding("UTF-8");  
	        resp.setCharacterEncoding("UTF-8");  
	        RequestDispatcher rd;
	        String wushangname=req.getParameter("username");
	        String passwd=req.getParameter("password");
	        String openid=req.getParameter("openid");
	        log.info(openid+"////openid");
	        /***先判断在数据库中是否有 绑定数据 如果已经绑定 那么就直接跳转？****/
	       String result= checkWushang(wushangname,passwd);
	        if(result.equals("登录成功")){
	        if(WxLinkWsuserImpl.findByOpenId(openid)==null){
	        	log.info("******绑定******"+wushangname+passwd);
	        	  WxLinkWsuserImpl.save(openid, wushangname, passwd);
	        	  rd=req.getRequestDispatcher("/user.jsp");
	        	  req.setAttribute("result", "您绑定成功");
	        	  rd.forward(req, resp);
	        	  //转发到绑定成功页面
	        }else{
	        	//顾客已经绑定了 
	        	String login=WeixinConstants.TouchLogin;
	        	login=login.replace("ID", wushangname);
	        	login=login.replace("PASSWd", passwd);
	        	resp.sendRedirect(login);
	        }
	        }else{
	        	 rd=req.getRequestDispatcher("/user.jsp");
	        	 req.setAttribute("result", result);
	        	 rd.forward(req, resp);
	        }
	        
	}
	
	
	/**关于绑定 验证 如果武商网登录不上去 ，那么提示用户重新输入***/
	public String checkWushang(String wushangname,String wushangpasswd){
	String	result ="帐号错误";
	String url=WeixinConstants.login_url;
	//String url=WeixinConstants.login_url_test;
    try {
		url=url.replace("ID", URLEncoder.encode(wushangname, "utf-8"));
		url=url.replace("PASSWD", URLEncoder.encode(wushangpasswd, "utf-8"));
	} catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
   // String a=httpRequest(url, "GET");
   // log.info("result is"+ a);
    JSONObject jso=JSONObject.fromObject(httpRequest(url, "GET"));
//	JSONObject jso=JSONObject.fromObject(a);
	int status=jso.getInt("status");
	switch (status) {
	case 100:
		result="登录成功";
		break;
		
	case 102:
		result="随机数为空";
		break;
		
	case 103:
		result="密码为空";
		break;
	case 104:
		result="用户名或密码错误";
		break;
	case 105:
		result="用户名不存在";
		break;
	default:
		result="不知名错误";
		break;
	}
	return result;
	}
	
	/**
	 * 发送http请求
	 * @param requestUrl 请求地址
	 * @return String 
	 * 请求参数 方法  请求用户名密码
	 */
	public static String httpRequest(String requestUrl,String methode) {
		log.info("发起请求的url是："+requestUrl);
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
		
			httpUrlConn.setRequestMethod(methode.toUpperCase());
			httpUrlConn.connect();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
            
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return buffer.toString();
	}
	public static void main(String[] args) {
		String url=WeixinConstants.login_url_test;
	    url=url.replace("ID", "popolzrstc");
	    url=url.replace("PASSWD", "popolzr");
	    //{"status":3}
	    JSONObject jso=JSONObject.fromObject(httpRequest(url, "GET"));
		int status=jso.getInt("status");
		System.out.println(status);
	}
}
