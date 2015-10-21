package org.zr.others;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.KuaidiBean;
import org.zr.service.FaceService;
import org.zr.util.WeixinConstants;

public class KuaiDi {
	private static Logger log=LoggerFactory.getLogger(KuaiDi.class);
/**��ݲ�ѯ�� ����type ��ݹ�˾���� number��ݺ���  **/
	public static String kD100(String type,String number){
		String url=WeixinConstants.Kuaidi_url;
		StringBuffer buffer = new StringBuffer();
	
		try {
			url=url.replace("number",number);
			if(type.contains("��ͨ")||type.equals("��ͨ")){
				url=url.replace("company",WeixinConstants.zhongtongkd);
			}else if(type.contains("Բͨ")||type.equals("Բͨ")){
				url=url.replace("company",WeixinConstants.yuantongkd);
			}else if(type.contains("��ͨ")||type.equals("��ͨ")){
				url=url.replace("company",WeixinConstants.shentongkd);
			}else if(type.contains("�°�")||type.equals("�°�")){
				url=url.replace("company",WeixinConstants.debangkd);
			}else if(type.contains("�ϴ�")||type.equals("�ϴ�")){
				url=url.replace("company",WeixinConstants.yundakd);
			}else if(type.contains("˳��")||type.equals("˳��")){
				url=url.replace("company",WeixinConstants.shunfengkd);
			}else if(type.contains("ems")||type.equalsIgnoreCase("ems")){
				url=url.replace("company",WeixinConstants.emskd);
			}else if(type.contains("����")||type.equals("����")){
				url=url.replace("company",WeixinConstants.tiantiankd);
			}else if(type.contains("��ͨ")||type.equals("��ͨ")){
				url=url.replace("company",WeixinConstants.huitongkuaidikd);
			}else if(type.contains("�ٶ�")||type.equals("�ٶ�")){
				url=url.replace("company",WeixinConstants.suekd);
			}else if(type.contains("լ����")||type.equals("լ����")){
				url=url.replace("company",WeixinConstants.zhaijisongkd);
			}else if(type.contains("��ͨ")||type.equals("��ͨ")){
				url=url.replace("company",WeixinConstants.yuntongkuaidi);
			}else if(type.contains("ȫ��")||type.equals("ȫ��")){
				url=url.replace("company",WeixinConstants.quanfeng);
			}
			else{
				log.info("û�в鵽���");
				return "��ʱû�д˿��";
			}
			
		//	StringBuffer buffer = new StringBuffer();
			String json=FaceService.httpRequest(url);
			JSONObject jsobject = JSONObject.fromObject(json);
			String message=jsobject.getString("message");//message=ok ��ôȡֵ�ɹ�
			if(message.equals("ok")){
			String nu=jsobject.getString("nu");
			String check=jsobject.getString("ischeck");
			JSONArray jsonArray=JSONObject.fromObject(jsobject).getJSONArray("data");
			buffer.append("�˵����:"+nu+"\n");
			buffer.append("��ݹ�˾:"+type+"\n");
			if(check.equals("1")){
				buffer.append("��״̬����ǩ��"+"\n");
			}else{
				buffer.append("��״̬������ת��"+"\n");
			}
		//	String content=jsobject.getString("content");
			//for(Object obj : jsonArray){
				//JSONObject jsobj=(JSONObject)obj;
			//	buffer.append(jsobj.getString("context"));
			//	buffer.append(jsobj.getString("time")+"\n");
			//}
			
			return buffer.toString();
			
			//	content=content.replace("{br}", "");
			//	content=content.replace("��", "��"+"\n");
				//content=content.replace("��", ":");
				//content=content.replace("��", "\n");
				//return content;
			}else{
				return message;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return buffer.toString();
		
		
		
	}
	
}
