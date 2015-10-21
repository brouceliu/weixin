package org.zr.upload;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.zr.entity.WeixinMedia;
import org.zr.util.WeixinConstants;



public class UploadMedia {

	public static WeixinMedia upload(String accesstoken,String type,String mediaFileurl){
		WeixinMedia wxmd=null;
		String uploadurl=WeixinConstants.UploadLogo;
		uploadurl=uploadurl.replace("ACCESS_TOKEN", accesstoken);
		//定义数据分隔符
		String boundary="----2WyPehDimq70X3Lw";
		try {
			URL urllogo=new URL(uploadurl);
			HttpURLConnection conn=(HttpURLConnection) urllogo.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);	
            OutputStream out=conn.getOutputStream();
            
			//从图片url 读取图片
            URL mediaurl=new URL(mediaFileurl);
            HttpURLConnection connet=(HttpURLConnection) mediaurl.openConnection();
			connet.setDoOutput(true);
			connet.setRequestMethod("GET");
			//获取请求头不
			String contentType=connet.getHeaderField("Content-Type");
			String fileExt=getFileExt(contentType);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFileExt(String contentType){
		String fileExt="he";
		if("image/jpeg".equals(contentType)){
			fileExt=".jpg";
			}
		
		return fileExt;
	}
}
