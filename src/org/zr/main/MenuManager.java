package org.zr.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.AccessToken;
import org.zr.entity.Button;
import org.zr.entity.CommonButton;
import org.zr.entity.ComplexButton;
import org.zr.entity.Menu;
import org.zr.entity.ViewButton;
import org.zr.util.TokenUtil;
import org.zr.util.WeixinConstants;
import org.zr.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = WeixinConstants.appid;
		// 第三方用户唯一凭证密钥
		String appSecret =WeixinConstants.appSecret;

		// 调用接口获取access_token
		AccessToken at = TokenUtil.getAccessToken(appId, appSecret);
		
		// anpjYnJF-zQJVd8Be41UXlI-6Jo7cCPqjNdttkKHPtxuclwxDCBgJuH32OtYsi9gfObr87erFeuYPN3ghLcYLodFOAOxz88_Sc5kQUTnOqA
		// 调用接口创建菜单 9：44
		if (null != at) {  
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
			System.out.println(at.getToken()+"***");
			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
		
		//删除菜单
		/*if(null !=at){
			System.out.println(at.getToken()+"***");
			int result=WeixinUtil.deleteMenu(at.getToken());
			if (0 == result)
				log.info("菜单删除成功！");
			else
				log.info("菜单删除失败，错误码：" + result);
		}
		*/
		
	}

	/***
	 * 返回apptoken
	 * ***/
	public static String retirnToken(){
		// 第三方用户唯一凭证
				String appId = WeixinConstants.appid;
				// 第三方用户唯一凭证密钥
				String appSecret = WeixinConstants.appSecret;

				// 调用接口获取access_token
				AccessToken at = TokenUtil.getAccessToken(appId, appSecret);
				
		return at.getToken();
		
	}
	
	
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("天气预报");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("功能介绍");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("我的订单");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("武商网介绍");
		btn14.setType("click");
		btn14.setKey("14");

		

		CommonButton btn24 = new CommonButton();
		btn24.setName("人脸识别");
		btn24.setType("click");
		btn24.setKey("24");
		
		CommonButton btn33 = new CommonButton();
		btn33.setName("安卓版app下载");
		btn33.setType("click");
		btn33.setKey("33");
		

		CommonButton btn38 = new CommonButton();
		btn38.setName("武商网会员服务");
		btn38.setType("click");
		btn38.setKey("37");
	
	
		ViewButton btn31 = new ViewButton();
		btn31.setName("武商网微社区");
		btn31.setType("view");
		btn31.setUrl("http://m.wsq.qq.com/210912906?code=0412bb65b85fe8c96d61213a0f6eb440&state=1");
		
		
		ViewButton btn32 = new ViewButton();
		btn32.setName("武商网触屏版");
		btn32.setType("view");
		btn32.setUrl("http://www.wushang.com/");
		
		/*ViewButton btn35 = new ViewButton();
		btn35.setName("一元众筹赢珠宝");
		btn35.setType("view");
		btn35.setUrl("http://t.cn/RLuNK3x");*/
		
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("2048游戏");
		btn34.setType("view");
		btn34.setUrl("http://119.79.233.21/Txwx/index.html");
		
		ViewButton btn36 = new ViewButton();
		btn36.setName("core-ball");
		btn36.setType("view");
		btn36.setUrl("http://timelineapp.pointstone.org/coreball/game.html?openid=o3OtAuK--ELj5XEt3w22NGpb6Jv8&from=timeline&isappinstalled=0");
		
	

		
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn14,btn12});//btn13,btn12,

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("游戏娱乐");
		mainBtn2.setSub_button(new Button[] { btn24 ,btn34,btn36});//btn21,btn22, btn23,btn25 

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("武商网服务");
	//	mainBtn3.setName("");
		mainBtn3.setSub_button(new Button[] {btn32,btn31,btn38,btn13});//btn32,,btn33,btn35
		
		

	/**注意微信号只支持3按钮，超过3个无法创建***/

		
		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * z
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 ,mainBtn3 });//, mainBtn3 

		return menu;
	}
	
	
	//删除菜单
	
	
	
}