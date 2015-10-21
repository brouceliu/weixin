package org.zr.others;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.zr.service.FaceService;

public class GetChat {

	public static String chat(String msg){
		String url="http://api.qingyunke.com/api.php?key=free&appid=0&msg=关键词";
		try {
			url=url.replace("关键词", java.net.URLEncoder.encode(msg, "UTF-8"));
			String json=FaceService.httpRequest(url);
			JSONObject jsobject = JSONObject.fromObject(json);
			String result=jsobject.getString("result");
			String content=jsobject.getString("content");
			if(result.equals("0")){
				content=content.replace("{br}", "");
				content=content.replace("。", "。"+"\n");
				content=content.replace("：", ":");
				
				//content=content.replace("。", "\n");
				return content;
			}else{
				return "请您发送文明消息，做文明市民～";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "请您发送文明消息，做文明市民～";
		
	}
}
