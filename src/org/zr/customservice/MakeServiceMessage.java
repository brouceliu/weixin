package org.zr.customservice;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.message.zr.response.Article;
import org.message.zr.response.Music;

public class MakeServiceMessage {
/**组装客服消息
 * @return 
 * **/
	public static String makeTextCustomMessage(String openid,String content){
		//对消息内容中双引号进行转义
		content = content.replace("\"","\\\"");
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openid,content);
	}
	/**组装图片客服消息 媒体文件id mediaid   openid 消息发送对象**/
	public static String makeImageCustomMessage(String openid,String mediaid){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openid,mediaid);
	}
	
	/**组装语音客服消息 媒体文件id mediaid   openid 消息发送对象**/
	public static String makeVoiceCustomMessage(String openid,String mediaId){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openid,mediaId);
	}
	
	/**组装视频客服消息**/
	public static String makeVedioCustomMessage(String openid,String mediaId,String thumbMediaId){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openid,mediaId,thumbMediaId);
	}
	
	/**组装音乐客服消息**/
	public static String makeMusicCustomMessage(String openid,Music music){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":\"%s\"}}";
		jsonMsg=String.format(jsonMsg, openid,JSONObject.fromObject(music.toString()));
		//参数名称替换
		jsonMsg.replace("musicUrl", "musicurl");
		jsonMsg.replace("HQMusicUrl", "hqmusicurl");
		jsonMsg.replace("thumbMediaId", "thumb_media_id");
		return jsonMsg;
	}
	
	/**组装图片加文字客服消息**/
	public static String makeNewsCustomMessage(String openid,List<Article> articleList){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\" articles\":%s}}";
		jsonMsg=String.format(jsonMsg, openid,JSONArray.fromObject(articleList).toString().replaceAll("\"","\\\""));
		//参数名称替换
		jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}
	
	
}
