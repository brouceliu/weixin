package org.zr.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.AccessToken;
import org.zr.model.dao.AccessTokenImpl;

public class TokenUtil {
	private static Logger log = LoggerFactory.getLogger(TokenUtil.class);
	/**//**请求accesstoken 并且 判断时间是否有效，无效的话就重新获取**//*
	public static AccessToken getAccessToken() {
		*//**
		 * 向数据库请求数据，判断数据库是否存在token不存在则直接请求
		 *  存在 那么先取得token时间 ，比对时间，判断时间有效与否
		 *  时间如果不对，那么就重新取得时间，并且存入数据库
		 * ***//*
		     AccessToken token=AccessTokenImpl.selectToken();
	        log.info("数据库查询到的token是"+token.getToken());
			if(null==token.getToken()||token.getToken().equals("")){
				log.info("*****token is null*****");
				token=TokenUtil.getAccessToken(WeixinConstants.appid, WeixinConstants.appSecret);
				AccessTokenImpl.insertoken(token);// 存进数据库
			}else{
				log.info(token.getGettime()+token.getExpiresIn());
				String time=token.getGettime();
				Boolean result=TimeUtil.checksTime(time);
				if(result==false){
					log.info("*****token timeout*****retry");
					token=TokenUtil.getAccessToken(WeixinConstants.appid, WeixinConstants.appSecret);
					AccessTokenImpl.insertoken(token);// 存进数据库
				}
			}
		
       
		
		return token;
		
	}
	*//**
	 * 获取access_token
	 * 对服务器请求 token
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		
		//DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String gettime=df.format(date);
		//**用来向服务器请求token**//
		
        log.info("******************向微信服务器请求 accesstoken *************************");
		String requestUrl = WeixinConstants.access_token_url;
		requestUrl=requestUrl.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				log.info("请求accesstoken已成功");
				accessToken = new AccessToken();
				//**设置token**
				accessToken.setToken(jsonObject.getString("access_token"));
				//**设置token有效时间**//*
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				//**取得现在的时间 然后加上有效时间 得到最后生效时间***//*
				Date date = new Date(); 
				SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				date=TimeUtil.addOneSecond(date, jsonObject.getInt("expires_in")-200);
				//**设置最后生效时间**//*
				accessToken.setGettime(from.format(date));
				AccessTokenImpl.insertoken(accessToken);// 存进数据库
				log.info("有效时间到"+from.format(date));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.warn("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	
	
	
	
	
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		//
		//log.info("https请求url是"+requestUrl);
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			//if ("GET".equalsIgnoreCase(requestMethod))
				//httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		//	log.error("连接超时",ce);
		} catch (Exception e) {
		//	log.error("请求异常", e);
		}
		return jsonObject;
	}
	
}
