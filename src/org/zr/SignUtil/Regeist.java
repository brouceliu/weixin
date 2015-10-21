package org.zr.SignUtil;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.zr.main.MenuManager;
import org.zr.util.CusHttpUtil;
import org.zr.util.WeixinUtil;

public class Regeist {
//ע�Ṥ��   ע��΢��ҡһҡ
	public static void reg(){
		String url="https://api.weixin.qq.com/shakearound/account/register?access_token=ACCESS_TOKEN";
	//	String token=MenuManager.retirnToken();
		String token="qBhgEohw7-_P-ldGSoSfUy5BDuS7jQufAVDNuegsbbFeVOBPH85zHlkYlval45GEkRG9rJPP0fTIMr2b8xSZOTcTG90aXbRfxKH9RPDQ-_A";
		url = url.replace("ACCESS_TOKEN", token);
		JSONArray js=new JSONArray();
		
		JSONObject podata=new JSONObject();
		podata.put("name", "��ΰ");
		podata.put("phone_number", "18607110741");
		podata.put("email", "whleo@qq.com");
		podata.put("industry_id", "0101");
		podata.put("qualification_cert_urls", js);
		podata.put("apply_reason", "Ӫ����Ҫ��ҡһҡ����");
		String result = WeixinUtil.httpRequest(url, "POST", podata.toString()).toString();
		System.out.println(result);
	}
	
	
	
	public static String GetStatus(){
		String url="https://api.weixin.qq.com/shakearound/account/auditstatus?access_token=ACCESS_TOKEN";
		String token=MenuManager.retirnToken();
		System.out.println(token);
		url=url.replace("ACCESS_TOKEN", token);
		String result = WeixinUtil.httpRequest(url, "POST", null).toString();
		return result;
	}
	
	/**����Ҫ�ϴ� ������ֵ���֤  �õ�������url   �����ֶ��ϴ���ͼƬ�� ***/
	public static void uploadimg(){
		
	}
	
	public static String send(String url, String filePath) throws IOException {


		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			System.out.println("�ļ�������");
		throw new IOException("�ļ�������");
		
		}
		
		// ����
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();


		/**
		* ���ùؼ�ֵ
		*/
		con.setRequestMethod("POST"); // ��Post��ʽ�ύ����Ĭ��get��ʽ
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post��ʽ����ʹ�û���


		// ��������ͷ��Ϣ
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");


		// ���ñ߽�
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);


		// ����������Ϣ


		// ��һ���֣�
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // �����������
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
		+ file.getName() + "\"\r\n");
		System.out.println(file.getName());
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");


		// ��������
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// �����ͷ
		out.write(head);


		// �ļ����Ĳ���
		// ���ļ������ļ��ķ�ʽ ���뵽url��
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
		out.write(bufferOut, 0, bytes);
		}
		in.close();


		// ��β����
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ����������ݷָ���


		out.write(foot);


		out.flush();
		out.close();


		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
		// ����BufferedReader����������ȡURL����Ӧ
		reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
		//System.out.println(line);
		buffer.append(line);
		}
		if(result==null){
			
		result = buffer.toString();
		}
		} catch (IOException e) {
		System.out.println("����POST��������쳣��" + e);
		e.printStackTrace();
		throw new IOException("���ݶ�ȡ�쳣");
		} finally {
		if(reader!=null){
		reader.close();
		}

		}


		return result;


		}
		
		
		
	
	
	
	
	public static void main(String[] args) {
		/*String filepath="C:/Users/DELL/Desktop/FileRecv/22.jpg";
		String sendurl="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	//	String token=MenuManager.retirnToken();
		
		sendurl=sendurl.replace("ACCESS_TOKEN", "uFSlQPBx6eahSSY3l7mP238fj0x9TXmPdCbLTMorKA79rhpEA1XxYWGk2HwYQ_mwBkAGKNoNHafCj6SmdhNuNsnHdRbiLcV8KT3uFctze3g").replace("TYPE", "image");
		String result = "11111";
		System.out.println(sendurl);
		try {
			
			result = send(sendurl, filepath);
			System.out.println(result);
		
		} catch (Exception e) {
		
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println(result);*/
		System.out.println(GetStatus());
	}
	
}
