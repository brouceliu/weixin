package org.zr.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.AccessToken;
import org.zr.entity.CardInfo;
import org.zr.model.dao.CardInfoDao;
import org.zr.model.dao.CardInfoDaoImpl;
import org.zr.service.token.TokenService;
import org.zr.util.JsTicketUtil;
import org.zr.util.TimeUtil;
import org.zr.util.TokenUtil;
import org.zr.util.WeixinConstants;

import net.sf.json.JSONObject;

public class CreateCard {
	private static Logger log = LoggerFactory.getLogger(CreateCard.class);
	// 创建card 折扣卷
	//pwRFtt4C3O1LcbYQCjUY38Oy1VFM
	/**创建了折扣卷之后 把折扣卷的id type ***/
	public static String CreateDiscountCard(String logo, String discount,
			String name, String color, String notice, String desc, String sku,
			String start,String end) {
		start=TimeUtil.toUnixTime(start);
		end=TimeUtil.toUnixTime(end);
		
		String card_type = "DISCOUNT";// 类型 z折扣卷
	//	discount = "";// 几折
        String code_type="CODE_TYPE_TEXT";
        String brand_name="武商网";
        //String title=name;//卡卷名
        
        //组装数据
        JSONObject card1=new JSONObject();//最外层
        JSONObject card=new JSONObject();
        card.put("card_type", card_type);
      //  card.put("discount", discount);
        
        JSONObject distc=new JSONObject();
        
         //第二层
        JSONObject base_info=new JSONObject();
        base_info.put("logo_url", logo);
        base_info.put("brand_name", brand_name);
        base_info.put("code_type", code_type);
        base_info.put("title", name);
        base_info.put("color", color); 
        base_info.put("notice", notice);
        base_info.put("description", desc);
        
        //第四层 
        JSONObject sk=new JSONObject();
        sk.put("quantity",sku);
        
        base_info.put("sku", sk);
        
        //第四层
        JSONObject datas=new JSONObject();
        datas.put("type", "1");//1 固定日期区间 2 固定时长
        datas.put("begin_timestamp", start);//时间要转化unix 秒
        datas.put("end_timestamp", end);
        base_info.put("date_info", datas);
        base_info.put("use_custom_code", false);//是否自定义
        base_info.put("get_limit", "100");//限制领卷数目
        base_info.put("can_give_friend", false);//卡卷可否转赠
        
        distc.put("base_info", base_info);
        distc.put("discount", discount);
    
        card.put("discount", distc);
        card1.put("card", card);
        
        //发送请求  取得 请求结果
        String url=WeixinConstants.CreateCartUrl;
        
        AccessToken token=new AccessToken();
		token=TokenService.getAccesstoken();
		String toke=token.getToken(); 
        url=url.replace("ACCESS_TOKEN", toke);
		
        JSONObject jsonObject = TokenUtil.httpRequest(url, "POST", card1.toString());
       if(jsonObject.get("errmsg").equals("ok")){
    	   String cardid=jsonObject.get("card_id").toString();
    	   CardInfoDao cdao=new CardInfoDaoImpl();
    	  CardInfo info=new CardInfo();
    	  String time=TimeUtil.getNowTime();
    	  info.setCardid(cardid);
    	  info.setCardsku(sku);
    	  info.setCardtime(time);
    	  info.setCardtype(card_type);
    	  cdao.addCardInfo(info);//把卡卷的信息id存储到cardinfo里面
    	   return cardid;
       }else{
    	   return jsonObject.get("errcode").toString()+jsonObject.get("errmsg").toString();
       }
	}

	//创建 代金卷
	
	/**创建了折扣卷之后 把折扣卷的id type ***/
	public static String CreateCashCard(String logo, String qiyong,String jianmian,
			String name, String color, String notice, String desc, String sku,
			String start,String end,String namefu, String c1,String curl1,String c2,String curl2) {
		start=TimeUtil.toUnixTime(start);
		end=TimeUtil.toUnixTime(end);
		 desc=desc.replace("|", "\n");
		 log.info("测试换行"+desc);
		String card_type = "CASH";// 类型 z折扣卷
	//	discount = "";// 几折
        String code_type="CODE_TYPE_TEXT";
        String brand_name="武商网";
        //String title=name;//卡卷名
        //组装数据
        JSONObject card1=new JSONObject();//最外层
        JSONObject card=new JSONObject();
        card.put("card_type", card_type);
      //  card.put("discount", discount);
        
        JSONObject distc=new JSONObject();
        
         //第二层
        JSONObject base_info=new JSONObject();
        base_info.put("logo_url", logo);
        base_info.put("brand_name", brand_name);
        base_info.put("code_type", code_type);
        base_info.put("title", name);
        base_info.put("sub_title", namefu);//副标题
        base_info.put("color", color); 
        base_info.put("notice", notice);
        base_info.put("description", desc);
       
        //第四层 
        JSONObject sk=new JSONObject();
        sk.put("quantity",sku);
        
        base_info.put("sku", sk);
        
        //第3层
        JSONObject datas=new JSONObject();
        datas.put("type", "1");//1 固定日期区间 2 固定时长
        datas.put("begin_timestamp", start);//时间要转化unix 秒
        datas.put("end_timestamp", end);
        
        base_info.put("date_info", datas);
        base_info.put("use_custom_code", true);//是否自定义
        base_info.put("custom_url_name", c1);
        base_info.put("custom_url", curl1);
        base_info.put("custom_url_sub_title", "更多惊喜");
        base_info.put("promotion_url_name", c2);
        base_info.put("promotion_url", curl2);
        base_info.put("can_give_friend", true);//是否可以转赠
        base_info.put("get_limit", "1");//每人可领取数量
        
        distc.put("base_info", base_info);
        distc.put("least_cost", qiyong);
        distc.put("reduce_cost", jianmian);
        card.put("cash", distc);
        card1.put("card", card);
        
        //发送请求  取得 请求结果
        String url=WeixinConstants.CreateCartUrl;
        
        AccessToken token=new AccessToken();
		token=TokenService.getAccesstoken();
		String toke=token.getToken(); 
        url=url.replace("ACCESS_TOKEN", toke);
		
        JSONObject jsonObject = TokenUtil.httpRequest(url, "POST", card1.toString());
       if(jsonObject.get("errmsg").equals("ok")){
    	   String cardid=jsonObject.get("card_id").toString();
    	   CardInfoDao cdao=new CardInfoDaoImpl();
     	  CardInfo info=new CardInfo();
     	  String time=TimeUtil.getNowTime();
     	  info.setCardid(cardid);
     	  info.setCardsku(sku);
     	  info.setCardtime(time);
     	  info.setCardtype(card_type);
     	  cdao.addCardInfo(info);//把卡卷的信息id存储到card num里面
    	   return cardid;
       }else{
    	   return jsonObject.get("errcode").toString()+jsonObject.get("errmsg").toString();
       }
	}

}
