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
//注册工具   注册微信摇一摇
	public static void reg(){
		String url="https://api.weixin.qq.com/shakearound/account/register?access_token=ACCESS_TOKEN";
	//	String token=MenuManager.retirnToken();
		String token="qBhgEohw7-_P-ldGSoSfUy5BDuS7jQufAVDNuegsbbFeVOBPH85zHlkYlval45GEkRG9rJPP0fTIMr2b8xSZOTcTG90aXbRfxKH9RPDQ-_A";
		url = url.replace("ACCESS_TOKEN", token);
		JSONArray js=new JSONArray();
		
		JSONObject podata=new JSONObject();
		podata.put("name", "何伟");
		podata.put("phone_number", "18607110741");
		podata.put("email", "whleo@qq.com");
		podata.put("industry_id", "0101");
		podata.put("qualification_cert_urls", js);
		podata.put("apply_reason", "营销需要有摇一摇功能");
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
	
	/**首先要上传 电信增值许可证  得到服务器url   可以手动上传到图片库 ***/
	public static void uploadimg(){
		
	}
	
	public static String send(String url, String filePath) throws IOException {


		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			System.out.println("文件不存在");
		throw new IOException("文件不存在");
		
		}
		
		// 连接
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();


		/**
		* 设置关键值
		*/
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存


		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");


		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);


		// 请求正文信息


		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
		+ file.getName() + "\"\r\n");
		System.out.println(file.getName());
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");


		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);


		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
		out.write(bufferOut, 0, bytes);
		}
		in.close();


		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线


		out.write(foot);


		out.flush();
		out.close();


		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
		// 定义BufferedReader输入流来读取URL的响应
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
		System.out.println("发送POST请求出现异常！" + e);
		e.printStackTrace();
		throw new IOException("数据读取异常");
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
