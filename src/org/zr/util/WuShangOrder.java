package org.zr.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.OrderBean;
import org.zr.others.KuaiDi;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class WuShangOrder {
	private static Logger log=LoggerFactory.getLogger(WuShangOrder.class);
	/***作为武商 订单类  传入 open id  得到基本订单信息***/
	public static String GetOrderInfo(String openid){
		String result1="";
		String result="未取得";
		String logininfo=WushangLogin.loginWushang(openid);
		if(logininfo.equals("no")){
			result="请绑定账号";
		}else if(logininfo.equals("服务器异常")){
			result="服务器异常";
		}else if(logininfo.equals("用户名或密码错误!")||logininfo.equals("密码错误")){
			result="用户名或密码错误!";
			
		}else if(logininfo.equals("用户随机数为空")||logininfo.equals("该用户不存在！")||logininfo.equals("用户未激活")){
			result="用户登录错误!";
		}else if(logininfo.equals("登录失败，未知错误")||logininfo.equals("非会员")||logininfo.equals("非系统管理员")){
			result="用户登录失败!";
		}else{
			//String order="http://128.0.1.7/templates/public/member/handle/weixin/Weixin_order_list_handler.jsp";
			String order=WeixinConstants.OrderUrl;
			String orderinfo=CusHttpUtil.httpRequest(order, "post", "wsid="+logininfo);
			JSONObject jsorder=JSONObject.fromObject(orderinfo);
			int n=jsorder.getInt("count");
			log.info("count is "+n);
			JSONArray array=jsorder.getJSONArray("orderList");
			n=array.size();
			log.info("size is "+n);
			int i=0;int k=0;
			for(Object obj : array){
				OrderBean ob=new OrderBean();
				JSONObject o = JSONObject.fromObject(obj);
		        JSONObject b=o.getJSONObject("states");
		        JSONObject j=b.getJSONObject("payState");
		        String name=j.getString("payStateName");
		        ob.setPaystatus(name);
		        String ordernumber=o.getString("aliasCode");
		        JSONObject processate=b.getJSONObject("processState");
		        String processname=processate.getString("state");
		        if(processname=="已出库" || processname.equals("已出库")){
		        	 JSONObject jarryun=o.getJSONObject("logisticsInfo");
		 	        if(jarryun.containsKey("delMerchantName") && jarryun.containsKey("billNo")){
		 	        	i=++i;
		 	        	result1=result1+"订单号:"+o.getString("aliasCode")+KuaiDi.kD100(jarryun.getString("delMerchantName"), jarryun.getString("billNo"))+"\n";
		 	        	
		 	        }
		        } else if(processname=="待审核" || processname.equals("待审核")){
		        	   k = ++k;
		        }
		        
		        
		        ob.setPaystatus(processname);
		        
		        JSONObject jm=o.getJSONObject("jMerchant");
		        
		        String Mname=jm.getString("name_cn");		       
		        ob.setSellername(Mname);		  		        		
		        JSONObject priceinfo=o.getJSONObject("priceInfo");		       
		        Double allprice=priceinfo.getDouble("fTotalOrderRealPrice");
		        ob.setAllprice(allprice);
			}
			 result = "您总共有"+n+"个订单"+"\n"+"有"+i+"个已出库订单"+"\n"+"有"+k+"个等待支付订单"+"\n"+result1;
			 
			 log.info(result);
		}
	
		return result;
	}
	
}