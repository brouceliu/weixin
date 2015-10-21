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
		/**�󶨵�ʱ����Ҫ��֤ �Ƿ�����Ч���������˺�***/
		    req.setCharacterEncoding("UTF-8");  
	        resp.setCharacterEncoding("UTF-8");  
	        RequestDispatcher rd;
	        String wushangname=req.getParameter("username");
	        String passwd=req.getParameter("password");
	        String openid=req.getParameter("openid");
	        log.info(openid+"////openid");
	        /***���ж������ݿ����Ƿ��� ������ ����Ѿ��� ��ô��ֱ����ת��****/
	       String result= checkWushang(wushangname,passwd);
	        if(result.equals("��¼�ɹ�")){
	        if(WxLinkWsuserImpl.findByOpenId(openid)==null){
	        	log.info("******��******"+wushangname+passwd);
	        	  WxLinkWsuserImpl.save(openid, wushangname, passwd);
	        	  rd=req.getRequestDispatcher("/user.jsp");
	        	  req.setAttribute("result", "���󶨳ɹ�");
	        	  rd.forward(req, resp);
	        	  //ת�����󶨳ɹ�ҳ��
	        }else{
	        	//�˿��Ѿ����� 
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
	
	
	/**���ڰ� ��֤ �����������¼����ȥ ����ô��ʾ�û���������***/
	public String checkWushang(String wushangname,String wushangpasswd){
	String	result ="�ʺŴ���";
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
		result="��¼�ɹ�";
		break;
		
	case 102:
		result="�����Ϊ��";
		break;
		
	case 103:
		result="����Ϊ��";
		break;
	case 104:
		result="�û������������";
		break;
	case 105:
		result="�û���������";
		break;
	default:
		result="��֪������";
		break;
	}
	return result;
	}
	
	/**
	 * ����http����
	 * @param requestUrl �����ַ
	 * @return String 
	 * ������� ����  �����û�������
	 */
	public static String httpRequest(String requestUrl,String methode) {
		log.info("���������url�ǣ�"+requestUrl);
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
		
			httpUrlConn.setRequestMethod(methode.toUpperCase());
			httpUrlConn.connect();
			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
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
