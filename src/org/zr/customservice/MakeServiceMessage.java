package org.zr.customservice;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.message.zr.response.Article;
import org.message.zr.response.Music;

public class MakeServiceMessage {
/**��װ�ͷ���Ϣ
 * @return 
 * **/
	public static String makeTextCustomMessage(String openid,String content){
		//����Ϣ������˫���Ž���ת��
		content = content.replace("\"","\\\"");
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openid,content);
	}
	/**��װͼƬ�ͷ���Ϣ ý���ļ�id mediaid   openid ��Ϣ���Ͷ���**/
	public static String makeImageCustomMessage(String openid,String mediaid){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openid,mediaid);
	}
	
	/**��װ�����ͷ���Ϣ ý���ļ�id mediaid   openid ��Ϣ���Ͷ���**/
	public static String makeVoiceCustomMessage(String openid,String mediaId){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openid,mediaId);
	}
	
	/**��װ��Ƶ�ͷ���Ϣ**/
	public static String makeVedioCustomMessage(String openid,String mediaId,String thumbMediaId){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openid,mediaId,thumbMediaId);
	}
	
	/**��װ���ֿͷ���Ϣ**/
	public static String makeMusicCustomMessage(String openid,Music music){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":\"%s\"}}";
		jsonMsg=String.format(jsonMsg, openid,JSONObject.fromObject(music.toString()));
		//���������滻
		jsonMsg.replace("musicUrl", "musicurl");
		jsonMsg.replace("HQMusicUrl", "hqmusicurl");
		jsonMsg.replace("thumbMediaId", "thumb_media_id");
		return jsonMsg;
	}
	
	/**��װͼƬ�����ֿͷ���Ϣ**/
	public static String makeNewsCustomMessage(String openid,List<Article> articleList){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\" articles\":%s}}";
		jsonMsg=String.format(jsonMsg, openid,JSONArray.fromObject(articleList).toString().replaceAll("\"","\\\""));
		//���������滻
		jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}
	
	
}
