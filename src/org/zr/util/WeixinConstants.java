package org.zr.util;

import org.zr.entity.WeatherBean;
import org.zr.service.CreateMessage;

public class WeixinConstants {
/**保存微信中的参数**/
	
	
		// 第三方用户唯一凭证密钥
	 public final static String appid = "wx6f1863a4b2802eea";
	 public final static String appSecret = "d730b0bc988e97fb76ddeabf03a169e0";
	 
	/**中通 圆通  德邦 韵达 顺丰 申通  ems 天天 汇通 速尔物流 运通快递   全峰**/ 
	 public final static String zhongtongkd = "zhongtong";
	 public final static String yuantongkd = "yuantong";
	 public final static String debangkd = "debangwuliu";
	 public final static String yundakd = "yunda"; 
	 public final static String shunfengkd = "shunfeng";  
	 public final static String shentongkd = "shento ng";
	 public final static String emskd = "ems";
	 public final static String quanfeng = "quanfengkuaidi";
	 public final static String tiantiankd = "tiantian";
	 public final static String huitongkuaidikd = "huitongkuaidi";
	 public final static String suekd = "sue";
	 public final static String zhaijisongkd = "zhaijisong";
	 public final static String yuntongkuaidi = "yuntongkuaidi";
	 /**快递的接口地址**/
	 public final static String Kuaidi_url = "http://www.kuaidi100.com/query?type=company&postid=number";
	 /**微信接口地址**/
	 /**access_token 接口调用凭证 openid 用户的唯一id号码**/
	 public final static String WeixinUser_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESSTOKEN&openid=OPENID";
	 
	 /**微信获取用户列表**/
	 public final static String WeixinUserList_url ="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESSTOKEN&next_openid=NEXTOPENID";

		// 获取access_token的接口地址（GET） 限200（次/天）
		public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	    //武商网用户登录接口
		//?loginId=id&password=word  这里必须用ip 否则无法连接
		public final static String login_url="http://128.0.1.5/templates/public/login/handle/phone/login_handler_android.jsp?loginId=ID&password=PASSWD";
		
		public final static String login_url_no="http://128.0.1.5/templates/public/login/handle/phone/login_handler_android.jsp"; 
		
		public final static String login_url_test="http://128.0.1.7/templates/public/login/handle/phone/login_handler_android.jsp?loginId=ID&password=PASSWD";
		
		
		// 武商网触屏版登录接口
		public final static String TouchLogin="http://www.wushang.com/mobile/weiXinLogin.html#act=ID&psw=PASSWd";
		
		public final static String TestUSerLoginUrl = "http://vote.wushang.com/weixin/weiXinLogin.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		
		public final static String USerLoginUrl = "http://www.wushang.com/weixin/weiXinLogin.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		//微信领卷跳转的页面地址
		public final static String WeixinJuanUrl="http://www.wushang.com/wxcard/wxcards.html#k=TICKET&n=NICK&s=SUIJI&card=CTT&i=CAID&sj=CARSJ&ct=CARDTIME&ty=CARDTYPE&cnum=NUMBER";
		//微信领卷地址
		public final static String WeixinGetCardUrl="http://www.wushang.com/WeixinTouch/jump.jsx?htmlName=memberCenter/getcard";
		public final static String WeixinCardMy="http://wx.wushang.com/Txwx/WeixinGetCard.jsp";
		
		//卡卷类型 折扣卷.
		public final static String Weixinzhekou="DISCOUNT";
		//微信订单页面登录地址
		public final static String TestUSerOrderUrl="http://vote.wushang.com/weixin/weiXinOrder.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		public final static String USerOrderUrl = "http://www.wushang.com/wx/wxorders.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		
		
		//微信 正式版订单接口
		public final static String OrderUrl="http://128.0.1.5/templates/public/member/handle/weixin/Weixin_order_list_handler.jsp";
		//微信 测试板订单接口
		public final static String OrderUrl_test="http://128.0.1.7/templates/public/member/handle/weixin/Weixin_order_list_handler.jsp";
		//微信请求jsapi 地址
		public final static String JSAPIURL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		//微信测试登录地址
		public final static String login_url_test_zero="http://128.0.1.7/templates/public/login/handle/phone/login_handler_android.jsp"; 
		
		/***微信js 签名需要的参数**/
	  //微信个人中心
		public final static String persioal_test="http://vote.wushang.com/WeixinTest/jump.jsx?htmlName=memberCenter/myHomeWeiXin";
		public final static String persioal="http://www.wushang.com/WeixinTouch/jump.jsx?htmlName=memberCenter/myHomeWeiXin";
		
		//上传的url 
		public final static String uploadmediaUrl="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
		
		//微信的卡卷ticket请求地址
		public final static String CardTicketUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";
		public final static String UploadLogo="https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
		//创建卡卷
		 public final static String CreateCartUrl="https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
		 public final static String  uname="wsadmin";
		 public final static String  password="85506069lzrstc";
		 public final static String  ucode="popolzrstc";
		 //核销卡卷
		 public final static String  Destroycard="https://api.weixin.qq.com/card/code/consume?access_token=TOKEN";
		 //更新卡卷
		 public final static String  Updatecard="https://api.weixin.qq.com/card/update?access_token=TOKEN";
		 //作废卡卷
		 public final static String  Canclecard="https://api.weixin.qq.com/card/code/unavailable?access_token=TOKEN";
		 
		 
		 //天气
		 public final static String  WeatherUrl="http://wx.wushang.com/Txwx/wheather.jsp";
		 
		 
		 
	 /**
		 * 主菜单
		 * 
		 * @return
		 */
		public static String getMainMenu() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("亲，终于等到你！欢迎您加入武商网粉丝大家庭！关注武商网优惠，畅享更多福利！您可以回复数字选择服务：").append("\n\n");
			buffer.append("1  天气预报").append("\n");
		//	buffer.append("2  武商历史介绍").append("\n");
			buffer.append("3  人脸识别").append("\n");
		//	buffer.append("4  输入ip地址可以直接查询ip").append("\n");
			//buffer.append("5  输入手机号码直接查询手机归属地").append("\n");
			buffer.append("6  输入笑话可以得到笑话").append("\n");
			buffer.append("7  周边搜索").append("\n");
		//	buffer.append("8  快递查询").append("\n");
			buffer.append("9  回复“客服”，联系武商网客服服务").append("\n");
			return buffer.toString();
		}

		public static String getUsage() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("人脸检测使用指南").append("\n\n");
			buffer.append("发送一张清晰的照片，就能帮你分析出种族、年龄、性别等信息").append("\n");
			buffer.append("快来试试你是不是长得太着急");
			return buffer.toString();

		}

		public static String getweather(WeatherBean wb) {
			if (wb.getCity() == null) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("您输入的城市有误");
				return buffer.toString();
			} else {
				StringBuffer buffer = new StringBuffer();
				buffer.append("今日" + wb.getCity()).append("天气").append("\n\n");
				buffer.append(wb.getWeatherdate()).append("\n");
			
				
			
				return buffer.toString();
			}

		}
		
		/**地理位置服务提示语**/
		public static String locationSay(){
			StringBuffer buffer=new StringBuffer();
			buffer.append("点击窗口底部的'+'按钮，选择位置，点击发送").append("\n\n");
			buffer.append("请您发送您的地理位置消息，我们将为您查找周边生活指南").append("\n\n");
			buffer.append("发送地理位置消息后，您还可以输入'附件+美食'，'附近+厕所'进行查询").append("\n\n");	
			return buffer.toString();
		}
		
		
		public static String locationEndSay(){
			StringBuffer buffer=new StringBuffer();
			buffer.append("我们成功接受到你的地理位置消息").append("\n");
			buffer.append("您可以输入例如：'附件美食'或者'附近停车场'进行查询了！").append("\n");
			return buffer.toString();
		}
		
		public static String kdSay(){
			StringBuffer buffer=new StringBuffer();
			buffer.append("请您输入快递公司+快递单号查询").append("\n");
			buffer.append("您可以输入例如：'圆通快递10585506069'或者'ems快递10585506069'进行查询了！").append("\n");
			return buffer.toString();
		}
		//武商网的会员中心按钮
		
		// 活动消息
		/***亲爱的粉丝，恭喜你获得武商网20元红包，赶快戳这里领取：http://t.cn/RAdC7l4**/
			public static String HuoDongOne(){
				StringBuffer buffer=new StringBuffer();
				buffer.append("亲爱的粉丝，恭喜你获得武商网20元红包").append("\n");
				buffer.append("<a href='http://t.cn/RAdC7l4'"+">赶快戳这里领取</a>").append("\n");
				return buffer.toString();
			}
			public static String HuoDongTwo(){
				StringBuffer buffer=new StringBuffer();
				buffer.append("亲，感谢您参加活动，系统会在三个工作日内将红包存入你的账户！如有疑问，请加入武商网QQ群286244738咨询。").append("\n");
				buffer.append("武商网520爱May节精彩活动正在进行中，满1000最高减300，登陆武商网www.wushang.com看看吧！").append("\n");
				return buffer.toString();
			}
}
