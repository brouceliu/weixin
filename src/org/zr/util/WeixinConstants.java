package org.zr.util;

import org.zr.entity.WeatherBean;
import org.zr.service.CreateMessage;

public class WeixinConstants {
/**����΢���еĲ���**/
	
	
		// �������û�Ψһƾ֤��Կ
	 public final static String appid = "wx6f1863a4b2802eea";
	 public final static String appSecret = "d730b0bc988e97fb76ddeabf03a169e0";
	 
	/**��ͨ Բͨ  �°� �ϴ� ˳�� ��ͨ  ems ���� ��ͨ �ٶ����� ��ͨ���   ȫ��**/ 
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
	 /**��ݵĽӿڵ�ַ**/
	 public final static String Kuaidi_url = "http://www.kuaidi100.com/query?type=company&postid=number";
	 /**΢�Žӿڵ�ַ**/
	 /**access_token �ӿڵ���ƾ֤ openid �û���Ψһid����**/
	 public final static String WeixinUser_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESSTOKEN&openid=OPENID";
	 
	 /**΢�Ż�ȡ�û��б�**/
	 public final static String WeixinUserList_url ="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESSTOKEN&next_openid=NEXTOPENID";

		// ��ȡaccess_token�Ľӿڵ�ַ��GET�� ��200����/�죩
		public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	    //�������û���¼�ӿ�
		//?loginId=id&password=word  ���������ip �����޷�����
		public final static String login_url="http://128.0.1.5/templates/public/login/handle/phone/login_handler_android.jsp?loginId=ID&password=PASSWD";
		
		public final static String login_url_no="http://128.0.1.5/templates/public/login/handle/phone/login_handler_android.jsp"; 
		
		public final static String login_url_test="http://128.0.1.7/templates/public/login/handle/phone/login_handler_android.jsp?loginId=ID&password=PASSWD";
		
		
		// �������������¼�ӿ�
		public final static String TouchLogin="http://www.wushang.com/mobile/weiXinLogin.html#act=ID&psw=PASSWd";
		
		public final static String TestUSerLoginUrl = "http://vote.wushang.com/weixin/weiXinLogin.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		
		public final static String USerLoginUrl = "http://www.wushang.com/weixin/weiXinLogin.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		//΢�������ת��ҳ���ַ
		public final static String WeixinJuanUrl="http://www.wushang.com/wxcard/wxcards.html#k=TICKET&n=NICK&s=SUIJI&card=CTT&i=CAID&sj=CARSJ&ct=CARDTIME&ty=CARDTYPE&cnum=NUMBER";
		//΢������ַ
		public final static String WeixinGetCardUrl="http://www.wushang.com/WeixinTouch/jump.jsx?htmlName=memberCenter/getcard";
		public final static String WeixinCardMy="http://wx.wushang.com/Txwx/WeixinGetCard.jsp";
		
		//�������� �ۿ۾�.
		public final static String Weixinzhekou="DISCOUNT";
		//΢�Ŷ���ҳ���¼��ַ
		public final static String TestUSerOrderUrl="http://vote.wushang.com/weixin/weiXinOrder.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		public final static String USerOrderUrl = "http://www.wushang.com/wx/wxorders.html#act=ID&psw=PASSWd&k=TICKET&n=NICK&s=SUIJI";
		
		
		//΢�� ��ʽ�涩���ӿ�
		public final static String OrderUrl="http://128.0.1.5/templates/public/member/handle/weixin/Weixin_order_list_handler.jsp";
		//΢�� ���԰嶩���ӿ�
		public final static String OrderUrl_test="http://128.0.1.7/templates/public/member/handle/weixin/Weixin_order_list_handler.jsp";
		//΢������jsapi ��ַ
		public final static String JSAPIURL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		//΢�Ų��Ե�¼��ַ
		public final static String login_url_test_zero="http://128.0.1.7/templates/public/login/handle/phone/login_handler_android.jsp"; 
		
		/***΢��js ǩ����Ҫ�Ĳ���**/
	  //΢�Ÿ�������
		public final static String persioal_test="http://vote.wushang.com/WeixinTest/jump.jsx?htmlName=memberCenter/myHomeWeiXin";
		public final static String persioal="http://www.wushang.com/WeixinTouch/jump.jsx?htmlName=memberCenter/myHomeWeiXin";
		
		//�ϴ���url 
		public final static String uploadmediaUrl="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
		
		//΢�ŵĿ���ticket�����ַ
		public final static String CardTicketUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";
		public final static String UploadLogo="https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
		//��������
		 public final static String CreateCartUrl="https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
		 public final static String  uname="wsadmin";
		 public final static String  password="85506069lzrstc";
		 public final static String  ucode="popolzrstc";
		 //��������
		 public final static String  Destroycard="https://api.weixin.qq.com/card/code/consume?access_token=TOKEN";
		 //���¿���
		 public final static String  Updatecard="https://api.weixin.qq.com/card/update?access_token=TOKEN";
		 //���Ͽ���
		 public final static String  Canclecard="https://api.weixin.qq.com/card/code/unavailable?access_token=TOKEN";
		 
		 
		 //����
		 public final static String  WeatherUrl="http://wx.wushang.com/Txwx/wheather.jsp";
		 
		 
		 
	 /**
		 * ���˵�
		 * 
		 * @return
		 */
		public static String getMainMenu() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("�ף����ڵȵ��㣡��ӭ��������������˿���ͥ����ע�������Żݣ�������ร���������Իظ�����ѡ�����").append("\n\n");
			buffer.append("1  ����Ԥ��").append("\n");
		//	buffer.append("2  ������ʷ����").append("\n");
			buffer.append("3  ����ʶ��").append("\n");
		//	buffer.append("4  ����ip��ַ����ֱ�Ӳ�ѯip").append("\n");
			//buffer.append("5  �����ֻ�����ֱ�Ӳ�ѯ�ֻ�������").append("\n");
			buffer.append("6  ����Ц�����Եõ�Ц��").append("\n");
			buffer.append("7  �ܱ�����").append("\n");
		//	buffer.append("8  ��ݲ�ѯ").append("\n");
			buffer.append("9  �ظ����ͷ�������ϵ�������ͷ�����").append("\n");
			return buffer.toString();
		}

		public static String getUsage() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("�������ʹ��ָ��").append("\n\n");
			buffer.append("����һ����������Ƭ�����ܰ�����������塢���䡢�Ա����Ϣ").append("\n");
			buffer.append("�����������ǲ��ǳ���̫�ż�");
			return buffer.toString();

		}

		public static String getweather(WeatherBean wb) {
			if (wb.getCity() == null) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("������ĳ�������");
				return buffer.toString();
			} else {
				StringBuffer buffer = new StringBuffer();
				buffer.append("����" + wb.getCity()).append("����").append("\n\n");
				buffer.append(wb.getWeatherdate()).append("\n");
			
				
			
				return buffer.toString();
			}

		}
		
		/**����λ�÷�����ʾ��**/
		public static String locationSay(){
			StringBuffer buffer=new StringBuffer();
			buffer.append("������ڵײ���'+'��ť��ѡ��λ�ã��������").append("\n\n");
			buffer.append("�����������ĵ���λ����Ϣ�����ǽ�Ϊ�������ܱ�����ָ��").append("\n\n");
			buffer.append("���͵���λ����Ϣ��������������'����+��ʳ'��'����+����'���в�ѯ").append("\n\n");	
			return buffer.toString();
		}
		
		
		public static String locationEndSay(){
			StringBuffer buffer=new StringBuffer();
			buffer.append("���ǳɹ����ܵ���ĵ���λ����Ϣ").append("\n");
			buffer.append("�������������磺'������ʳ'����'����ͣ����'���в�ѯ�ˣ�").append("\n");
			return buffer.toString();
		}
		
		public static String kdSay(){
			StringBuffer buffer=new StringBuffer();
			buffer.append("���������ݹ�˾+��ݵ��Ų�ѯ").append("\n");
			buffer.append("�������������磺'Բͨ���10585506069'����'ems���10585506069'���в�ѯ�ˣ�").append("\n");
			return buffer.toString();
		}
		//�������Ļ�Ա���İ�ť
		
		// ���Ϣ
		/***�װ��ķ�˿����ϲ����������20Ԫ������Ͽ��������ȡ��http://t.cn/RAdC7l4**/
			public static String HuoDongOne(){
				StringBuffer buffer=new StringBuffer();
				buffer.append("�װ��ķ�˿����ϲ����������20Ԫ���").append("\n");
				buffer.append("<a href='http://t.cn/RAdC7l4'"+">�Ͽ��������ȡ</a>").append("\n");
				return buffer.toString();
			}
			public static String HuoDongTwo(){
				StringBuffer buffer=new StringBuffer();
				buffer.append("�ף���л���μӻ��ϵͳ���������������ڽ������������˻����������ʣ������������QQȺ286244738��ѯ��").append("\n");
				buffer.append("������520��May�ھ��ʻ���ڽ����У���1000��߼�300����½������www.wushang.com�����ɣ�").append("\n");
				return buffer.toString();
			}
}
