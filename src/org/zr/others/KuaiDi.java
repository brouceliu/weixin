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
/**快递查询类 输入type 快递公司名称 number快递号码  **/
	public static String kD100(String type,String number){
		String url=WeixinConstants.Kuaidi_url;
		StringBuffer buffer = new StringBuffer();
	
		try {
			url=url.replace("number",number);
			if(type.contains("中通")||type.equals("中通")){
				url=url.replace("company",WeixinConstants.zhongtongkd);
			}else if(type.contains("圆通")||type.equals("圆通")){
				url=url.replace("company",WeixinConstants.yuantongkd);
			}else if(type.contains("申通")||type.equals("申通")){
				url=url.replace("company",WeixinConstants.shentongkd);
			}else if(type.contains("德邦")||type.equals("德邦")){
				url=url.replace("company",WeixinConstants.debangkd);
			}else if(type.contains("韵达")||type.equals("韵达")){
				url=url.replace("company",WeixinConstants.yundakd);
			}else if(type.contains("顺丰")||type.equals("顺丰")){
				url=url.replace("company",WeixinConstants.shunfengkd);
			}else if(type.contains("ems")||type.equalsIgnoreCase("ems")){
				url=url.replace("company",WeixinConstants.emskd);
			}else if(type.contains("天天")||type.equals("天天")){
				url=url.replace("company",WeixinConstants.tiantiankd);
			}else if(type.contains("汇通")||type.equals("汇通")){
				url=url.replace("company",WeixinConstants.huitongkuaidikd);
			}else if(type.contains("速尔")||type.equals("速尔")){
				url=url.replace("company",WeixinConstants.suekd);
			}else if(type.contains("宅急送")||type.equals("宅急送")){
				url=url.replace("company",WeixinConstants.zhaijisongkd);
			}else if(type.contains("运通")||type.equals("运通")){
				url=url.replace("company",WeixinConstants.yuntongkuaidi);
			}else if(type.contains("全峰")||type.equals("全峰")){
				url=url.replace("company",WeixinConstants.quanfeng);
			}
			else{
				log.info("没有查到快递");
				return "暂时没有此快递";
			}
			
		//	StringBuffer buffer = new StringBuffer();
			String json=FaceService.httpRequest(url);
			JSONObject jsobject = JSONObject.fromObject(json);
			String message=jsobject.getString("message");//message=ok 那么取值成功
			if(message.equals("ok")){
			String nu=jsobject.getString("nu");
			String check=jsobject.getString("ischeck");
			JSONArray jsonArray=JSONObject.fromObject(jsobject).getJSONArray("data");
			buffer.append("运单编号:"+nu+"\n");
			buffer.append("快递公司:"+type+"\n");
			if(check.equals("1")){
				buffer.append("现状态：已签收"+"\n");
			}else{
				buffer.append("现状态：正在转运"+"\n");
			}
		//	String content=jsobject.getString("content");
			//for(Object obj : jsonArray){
				//JSONObject jsobj=(JSONObject)obj;
			//	buffer.append(jsobj.getString("context"));
			//	buffer.append(jsobj.getString("time")+"\n");
			//}
			
			return buffer.toString();
			
			//	content=content.replace("{br}", "");
			//	content=content.replace("。", "。"+"\n");
				//content=content.replace("：", ":");
				//content=content.replace("。", "\n");
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
