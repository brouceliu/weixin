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
	/**//**����accesstoken ���� �ж�ʱ���Ƿ���Ч����Ч�Ļ������»�ȡ**//*
	public static AccessToken getAccessToken() {
		*//**
		 * �����ݿ��������ݣ��ж����ݿ��Ƿ����token��������ֱ������
		 *  ���� ��ô��ȡ��tokenʱ�� ���ȶ�ʱ�䣬�ж�ʱ����Ч���
		 *  ʱ��������ԣ���ô������ȡ��ʱ�䣬���Ҵ������ݿ�
		 * ***//*
		     AccessToken token=AccessTokenImpl.selectToken();
	        log.info("���ݿ��ѯ����token��"+token.getToken());
			if(null==token.getToken()||token.getToken().equals("")){
				log.info("*****token is null*****");
				token=TokenUtil.getAccessToken(WeixinConstants.appid, WeixinConstants.appSecret);
				AccessTokenImpl.insertoken(token);// ������ݿ�
			}else{
				log.info(token.getGettime()+token.getExpiresIn());
				String time=token.getGettime();
				Boolean result=TimeUtil.checksTime(time);
				if(result==false){
					log.info("*****token timeout*****retry");
					token=TokenUtil.getAccessToken(WeixinConstants.appid, WeixinConstants.appSecret);
					AccessTokenImpl.insertoken(token);// ������ݿ�
				}
			}
		
       
		
		return token;
		
	}
	*//**
	 * ��ȡaccess_token
	 * �Է��������� token
	 * @param appid ƾ֤
	 * @param appsecret ��Կ
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		
		//DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String gettime=df.format(date);
		//**���������������token**//
		
        log.info("******************��΢�ŷ��������� accesstoken *************************");
		String requestUrl = WeixinConstants.access_token_url;
		requestUrl=requestUrl.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				log.info("����accesstoken�ѳɹ�");
				accessToken = new AccessToken();
				//**����token**
				accessToken.setToken(jsonObject.getString("access_token"));
				//**����token��Чʱ��**//*
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				//**ȡ�����ڵ�ʱ�� Ȼ�������Чʱ�� �õ������Чʱ��***//*
				Date date = new Date(); 
				SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				date=TimeUtil.addOneSecond(date, jsonObject.getInt("expires_in")-200);
				//**���������Чʱ��**//*
				accessToken.setGettime(from.format(date));
				AccessTokenImpl.insertoken(accessToken);// ������ݿ�
				log.info("��Чʱ�䵽"+from.format(date));
			} catch (JSONException e) {
				accessToken = null;
				// ��ȡtokenʧ��
				log.warn("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	
	
	
	
	
	
	/**
	 * ����https���󲢻�ȡ���
	 * 
	 * @param requestUrl �����ַ
	 * @param requestMethod ����ʽ��GET��POST��
	 * @param outputStr �ύ������
	 * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		//
		//log.info("https����url��"+requestUrl);
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);

			//if ("GET".equalsIgnoreCase(requestMethod))
				//httpUrlConn.connect();

			// ����������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ���
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
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		//	log.error("���ӳ�ʱ",ce);
		} catch (Exception e) {
		//	log.error("�����쳣", e);
		}
		return jsonObject;
	}
	
}
