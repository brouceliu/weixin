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
 * �˵���������
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = WeixinConstants.appid;
		// �������û�Ψһƾ֤��Կ
		String appSecret =WeixinConstants.appSecret;

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = TokenUtil.getAccessToken(appId, appSecret);
		
		// anpjYnJF-zQJVd8Be41UXlI-6Jo7cCPqjNdttkKHPtxuclwxDCBgJuH32OtYsi9gfObr87erFeuYPN3ghLcYLodFOAOxz88_Sc5kQUTnOqA
		// ���ýӿڴ����˵� 9��44
		if (null != at) {  
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
			System.out.println(at.getToken()+"***");
			// �жϲ˵��������
			if (0 == result)
				log.info("�˵������ɹ���");
			else
				log.info("�˵�����ʧ�ܣ������룺" + result);
		}
		
		//ɾ���˵�
		/*if(null !=at){
			System.out.println(at.getToken()+"***");
			int result=WeixinUtil.deleteMenu(at.getToken());
			if (0 == result)
				log.info("�˵�ɾ���ɹ���");
			else
				log.info("�˵�ɾ��ʧ�ܣ������룺" + result);
		}
		*/
		
	}

	/***
	 * ����apptoken
	 * ***/
	public static String retirnToken(){
		// �������û�Ψһƾ֤
				String appId = WeixinConstants.appid;
				// �������û�Ψһƾ֤��Կ
				String appSecret = WeixinConstants.appSecret;

				// ���ýӿڻ�ȡaccess_token
				AccessToken at = TokenUtil.getAccessToken(appId, appSecret);
				
		return at.getToken();
		
	}
	
	
	/**
	 * ��װ�˵�����
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("����Ԥ��");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("���ܽ���");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("�ҵĶ���");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("����������");
		btn14.setType("click");
		btn14.setKey("14");

		

		CommonButton btn24 = new CommonButton();
		btn24.setName("����ʶ��");
		btn24.setType("click");
		btn24.setKey("24");
		
		CommonButton btn33 = new CommonButton();
		btn33.setName("��׿��app����");
		btn33.setType("click");
		btn33.setKey("33");
		

		CommonButton btn38 = new CommonButton();
		btn38.setName("��������Ա����");
		btn38.setType("click");
		btn38.setKey("37");
	
	
		ViewButton btn31 = new ViewButton();
		btn31.setName("������΢����");
		btn31.setType("view");
		btn31.setUrl("http://m.wsq.qq.com/210912906?code=0412bb65b85fe8c96d61213a0f6eb440&state=1");
		
		
		ViewButton btn32 = new ViewButton();
		btn32.setName("������������");
		btn32.setType("view");
		btn32.setUrl("http://www.wushang.com/");
		
		/*ViewButton btn35 = new ViewButton();
		btn35.setName("һԪ�ڳ�Ӯ�鱦");
		btn35.setType("view");
		btn35.setUrl("http://t.cn/RLuNK3x");*/
		
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("2048��Ϸ");
		btn34.setType("view");
		btn34.setUrl("http://119.79.233.21/Txwx/index.html");
		
		ViewButton btn36 = new ViewButton();
		btn36.setName("core-ball");
		btn36.setType("view");
		btn36.setUrl("http://timelineapp.pointstone.org/coreball/game.html?openid=o3OtAuK--ELj5XEt3w22NGpb6Jv8&from=timeline&isappinstalled=0");
		
	

		
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn14,btn12});//btn13,btn12,

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("��Ϸ����");
		mainBtn2.setSub_button(new Button[] { btn24 ,btn34,btn36});//btn21,btn22, btn23,btn25 

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("����������");
	//	mainBtn3.setName("");
		mainBtn3.setSub_button(new Button[] {btn32,btn31,btn38,btn13});//btn32,,btn33,btn35
		
		

	/**ע��΢�ź�ֻ֧��3��ť������3���޷�����***/

		
		/**
		 * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
		 * z
		 * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
		 * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 ,mainBtn3 });//, mainBtn3 

		return menu;
	}
	
	
	//ɾ���˵�
	
	
	
}