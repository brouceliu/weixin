package org.zr.util;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.zr.entity.OrderBean;
import org.zr.entity.WxLinkWs;
import org.zr.model.dao.*;
import org.zr.others.KuaiDi;
import org.zr.util.*;
public class WushangLogin {
/**武商网登录验证操作***/
	public static String  loginWushang(String openid){
		String loginDes="no";
	if (null != WxLinkWsuserImpl.findByOpenId(openid)) {
		loginDes="yes";
		WxLinkWs ws = WxLinkWsuserImpl.findByOpenId(openid);
		String passwd = ws.getWspasswd();
		String wushangname = ws.getWsuser();
		String url=WeixinConstants.login_url_no;
		 JSONObject jsresult= JSONObject.fromObject(CusHttpUtil.httpRequest(url, "post", "loginId="+wushangname+"&password="+passwd));
		    int status=jsresult.getInt("status");
	  
	    switch (status) {
        case 3:
            loginDes = "服务器异常";
            break;
        case 100: // 用户名验证正确，销毁当前activity
          
        	 loginDes = jsresult.getString("id");
            break;
        case 102:
            loginDes = "用户随机数为空";
            break;
        case 103:
            loginDes = "密码错误";
            break;
        case 104:
            loginDes = "用户名或密码错误！";
            break;
        case 105:
            loginDes = "该用户不存在！";
            break;
        case 106:
            loginDes = "用户未激活";
            break;
        case 107:
            loginDes = "非系统管理员";
            break;
        case 108:
            loginDes = "非会员";
            break;
        default:
            loginDes = "登录失败，未知错误";
            break;
    }
	    return loginDes;
	}else{
		return loginDes;
		
	}
	
	}
	/***test class***/
	public static String TestDingDan(String username ,String passwd){
		String loginDes="yes";	
		//String url=WeixinConstants.login_url_test_zero;
		String url=WeixinConstants.login_url_no;
		/*JSONObject js=new JSONObject();
		js.put("loginId", username);
		js.put("password", passwd);
	  */
		
	    JSONObject jsresult= JSONObject.fromObject(CusHttpUtil.httpRequest(url, "post", "loginId="+username+"&password="+passwd));
	    int status=jsresult.getInt("status");
	    switch (status) {
        case 3:
            loginDes = "服务器异常";
            break;
        case 100: // 用户名验证正确，销毁当前activity
           
        	 loginDes = jsresult.getString("id");
        	// loginDes = "success";
            break;
        case 102:
            loginDes = "用户随机数为空";
            break;
        case 103:
            loginDes = "密码错误";
            break;
        case 104:
            loginDes = "用户名或密码错误!";
            break;
        case 105:
            loginDes = "该用户不存在！";
            break;
        case 106:
            loginDes = "用户未激活";
            break;
        case 107:
            loginDes = "非系统管理员";
            break;
        case 108:
            loginDes = "非会员";
            break;
        default:
            loginDes = "登录失败，未知错误";
            break;
    }
	    return loginDes;
		
		
	}
	/***订单总体测试类
	 * 如果是已出库订单 给予物流查询服务
	 * **/
	public static void main(String[] args) {
		String id=TestDingDan("0014787","201218");
		System.out.println(id);
		String order="http://128.0.1.5/templates/public/member/handle/weixin/Weixin_order_list_handler.jsp";
		String result=CusHttpUtil.httpRequest(order, "post", "wsid="+id);
		JSONObject jsorder=JSONObject.fromObject(CusHttpUtil.httpRequest(order, "post", "wsid="+id));
		
		System.out.println(jsorder.getInt("count"));
		JSONArray array=jsorder.getJSONArray("orderList");
		int i=0;
		String resu="您的订单已出库";
		for(Object obj : array){
			OrderBean ob=new OrderBean();
			JSONObject o = JSONObject.fromObject(obj);
	        JSONObject b=o.getJSONObject("states");
	        JSONObject j=b.getJSONObject("payState");
	        String name=j.getString("payStateName");
	        i=++i;
	        ob.setPaystatus(name);  
	        JSONObject processate=b.getJSONObject("processState");
	        String processname=processate.getString("state");
	        ob.setPaystatus(processname);
	        System.out.println(o.getString("aliasCode"));
	        if(processname=="已出库" || processname.equals("已出库")){
	        	 System.out.println(processname);
	        	 JSONObject jarryun=o.getJSONObject("logisticsInfo");
	        	 System.out.println(jarryun.toString());
	 	        if(jarryun.containsKey("delMerchantName") && jarryun.containsKey("billNo")){
	 	        	System.out.println(jarryun.getString("delMerchantName"));
	 	        	
	 	        	resu=resu+"运单号是"+jarryun.getString("billNo")+"快递公司是"+jarryun.getString("delMerchantName")+"\n";
	 	        	System.out.println(KuaiDi.kD100(jarryun.getString("delMerchantName"), jarryun.getString("billNo")));
	 	        }
	        }
	        	       	         
	        JSONObject jm=o.getJSONObject("jMerchant");
	        
	        String Mname=jm.getString("name_cn");
	       
	        ob.setSellername(Mname);
	        JSONObject priceinfo=o.getJSONObject("priceInfo");
	       
	        Double allprice=priceinfo.getDouble("fTotalOrderRealPrice");
	        ob.setAllprice(allprice);
	        
		}
		System.out.println(resu);
	}
	
}
