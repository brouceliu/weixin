package org.zr.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.message.zr.response.Article;
import org.message.zr.request.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.chat.ChatService;
import org.zr.customservice.MakeServiceMessage;
import org.zr.customservice.SendCusMessage;
import org.zr.entity.AccessToken;
import org.zr.entity.BaiduPlace;
import org.zr.entity.UserLocation;
import org.zr.entity.WeatherBean;
import org.zr.model.dao.UserLocationDao;
import org.zr.model.dao.UserLocationDaoJdbc;
import org.zr.others.BaiduMapUtil;
import org.zr.others.GetChat;
import org.zr.others.GetHistory;
import org.zr.others.GetWheather;
import org.zr.others.KuaiDi;
import org.zr.service.picMessage.ReplyPicMeg;
import org.zr.service.token.TokenService;
import org.zr.util.JsTicketUtil;
import org.zr.util.MessageUtil;
import org.zr.util.WeixinConstants;
import org.zr.util.WuShangOrder;
import org.zr.util.WushangLogin;
import org.zr.util.XiaoqUtil;

/**
 * ���ķ����� �ٶ����� ak F9af0fcc4b18993fc7a6559683068b31
 * 
 * 
 */
public class CoreService {
	private static Logger log = LoggerFactory.getLogger(CoreService.class);

	// �������û�Ψһƾ֤
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = "";
		// AccessToken tokens=TokenUtil.getAccessToken();
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = respMessage;
			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);// �˴����쳣
			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			// �¼�����
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				log.info("�¼���Ϣ~~");
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = WeixinConstants.getMainMenu();
					respMessage = CreateMessage.CreateTextMessage(fromUserName,
							toUserName, respContent);

				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// O ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}

				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("11")) {
						// ����Ԥ��
						respContent = "�������� ����+�������� �人����";
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					} else if (eventKey.equals("12")) {
						respContent = WeixinConstants.getMainMenu();
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					} else if (eventKey.equals("13")) {
						/**
						 * �ҵĶ�����Ŀ �����ť ���� id �����û��� ���� ������� ����¼ ��ѯ�û������б�
						 * ��������Ҫ���û������������ʺ�
						 ***/
						String result = WushangLogin.loginWushang(fromUserName);
						log.info("******�û���¼�����****" + result);
						if (result.equals("no")) {
							// ��¼ʧ��
							String mes = "���ã���û�а��������˺�" + "\n" + "����������ʺ�";
							mes = mes
									+ "\n"
									+ "<a href='http://wx.wushang.com/Txwx/user.jsp"
									+ "?openid=" + fromUserName + "'"
									+ ">���Ұ�</a>";
							respContent = mes;
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						} else {
							// ��¼�ɹ�
							String mes = WuShangOrder
									.GetOrderInfo(fromUserName);
							log.info("login info is" + mes);
							respContent = mes
									+ "\n"
									+ "<a href='http://wx.wushang.com/Txwx/order.jsp"
									+ "?openid=" + fromUserName + "'"
									+ ">���Ҳ鿴����</a>";
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						}

					} else if (eventKey.equals("14")) {
						// ����������ʷ
						String mes = GetHistory.getwushang(toUserName,
								fromUserName);
						respMessage = mes;

					} else if (eventKey.equals("24")) {
						// ����ʶ��ť
						respContent = WeixinConstants.getUsage();
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					} else if (eventKey.equals("33")) {
						// ��׿app����
						respContent = WeixinConstants.getUsage();
					} else if (eventKey.equals("37")) {

						String mes = "���ã���ӭʹ�û�Ա���ܡ������������ֽ����Ա����" + "\n"
								+ "��һ������������ʺ�";
						mes = mes + "\n"
								+ "<a href='http://wx.wushang.com/Txwx/user.jsp"
								+ "?openid=" + fromUserName + "'" + ">���ҽ���</a>";
						respContent = mes;
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					}
				}
			} else {
				// ����ʱ��
				Long createTime = Long.parseLong(requestMap.get("CreateTime"));
				// ��Ϣid
				String mgid = requestMap.get("MsgId");
				// if (mgid != null && !mgid.equals("")) {
				Long gid = Long.parseLong(mgid);
				log.info("messageid is " + gid);
				// }
				// �ı���Ϣ
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//	if (XiaoqUtil.isRepeat(mgid, msgType, fromUserName,toUserName, createTime, gid)) {
						// �ı���Ϣ����
						String content = requestMap.get("Content");
						log.info(content);
						TextMessage textMessage = new TextMessage();
						textMessage.setToUserName(fromUserName);
						textMessage.setFromUserName(toUserName);
						textMessage.setCreateTime(createTime);
						textMessage
								.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						textMessage.setContent(content);
						// MessageDao mdao=new MessageDaoJdbc();

						// AccessToken tokens= TokenService.getAccesstoken();
						// UserInfo
						// us=GetWeiXinUser.getWeinxinUser(fromUserName,
						// tokens);
						// log.info(us.getNickname());
						// if(null !=us){
						// mdao.insertTextMessage(textMessage,us.getNickname());
						// }
						// �ж��û����͵��Ƿ��ǵ���QQ����
						if (XiaoqUtil.isQqFace(content)) {
							// �û���ʲôQQ���飬�ͷ���ʲôQQ����
							respContent = content;
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
							// ���ı���Ϣ����ת����xml�ַ���
							// MessageUtil.textMessageToXml(textMessage);
						} else if (content.contains("����")) {

							// �����첽�ķ�ʽ���û�����������Ϣ
							String[] ci = content.split("����");
							log.info("��ѯ��ʱ��ʼ");
							String msg = GetWheather.createWheatherMessage( fromUserName, toUserName,ci[0]);
						
							log.info("��ѯ��ʱ����");
							// ���û�����������Ϣ
							
							respMessage = msg;
						} else if (content.contains("��")) {

							String urls = WeixinConstants.persioal;
							String sh1ticket = JsTicketUtil.sha1Ticket(urls);
							String mes = "���ã���ӭʹ�û�Ա���ܡ�" + "\n"
									+ "��һ��ʹ�û�Ա���ģ��밴����ʾ���������ʺ�";
							mes = mes
									+ "\n"
									+ "<a href='http://wx.wushang.com/Txwx/user.jsp"
									+ "?openid=" + fromUserName + "&ticket="
									+ sh1ticket + "'" + ">���ҽ���</a>";
							respContent = mes;
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						} else if (content.equals("1")) {
							// ���û�����������Ϣ
							respContent = "�뷢�� ����+���� �� �人����";
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						} 
						/*else if (content.contains("���")) {
							// String company=content.replaceAll("���",
							// "").trim();
							String[] comment = content.split("���");
							// �����ݵ���
							respContent = KuaiDi.kD100(comment[0],
									comment[comment.length - 1]);
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						}*/ 
						
						else if (content.contains("�ͷ�")
								|| content.contains("kf")) {
							respMessage = CreateMessage.CreateCustomMessage(
									fromUserName, toUserName);
						}

						else if (content.equals("2")) {
							// ����������ʷ
							// String mes =
							// GetHistory.getwushang(toUserName,fromUserName);
							// ͼ����Ϣֱ�ӷ���
							String mes = GetHistory.getwushang(toUserName,
									fromUserName);
							respMessage = mes;

						} else if (content.equals("3")) {
							// ��������ʶ����Ϣ
							respContent = WeixinConstants.getUsage();
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						} else if (content.equals("7")) {

							// ���ص���λ����Ϣ��ʾ
							respContent = WeixinConstants.locationSay();
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						} else if (content.equals("8")) {

							// ���ؿ����Ϣ
							respContent = WeixinConstants.kdSay();
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						} 
						else if (ReplyPicMeg.findPicMeg(content)) {

							// ��ͼ����Ϣ
							

							ReplyPicMeg re=new ReplyPicMeg();
							respMessage=	re.replayMeg(content, fromUserName,toUserName);
						} 
						else if (content.startsWith("����")) {
							// �������λ�ò�ѯ
							String keyword = content.replaceAll("����", "")
									.trim();
							UserLocationDao userdao = new UserLocationDaoJdbc();
							UserLocation location = userdao
									.getLastlocation(fromUserName);
							if (null == location) {
								respContent = WeixinConstants.locationSay();
								respMessage = CreateMessage.CreateTextMessage(
										fromUserName, toUserName, respContent);
							} else {
								// ���ݾ�γ������
								List<BaiduPlace> placeList = BaiduMapUtil
										.searchPlace(keyword,
												location.getBd09Lat(),
												location.getBd09Lng());
								// û��������
								if (null == placeList || 0 == placeList.size()) {
									respContent = "������˼����������������";
									respMessage = CreateMessage
											.CreateTextMessage(fromUserName,
													toUserName, respContent);
								} else {

									List<Article> articleslist = BaiduMapUtil
											.makeArticleList(placeList,
													location.getBd09Lng(),
													location.getBd09Lat());
									String a = CreateMessage.BuildArticle(
											fromUserName, toUserName,
											articleslist);
									respMessage = a;
								}
							}

						} else {
							
							respContent = ChatService.chat(fromUserName, createTime.toString(), content);
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						
							//respMessage =CreateMessage.CreateCustomMessage(fromUserName,toUserName);
							
							
						}
					//}
				}

				// ͼƬ��Ϣ
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
					
			if (XiaoqUtil.isRepeat(mgid, msgType, fromUserName,
							toUserName, createTime, gid)) {
						log.info("face ############### start");
						/** isrepeat �д洢 **/
						String op = fromUserName;
						log.info("id is " + op);
						// ȡ��ͼƬ��ַ �첽��������ʶ����Ϣ
						String picUrl = requestMap.get("PicUrl");
						String detectResult = FaceService.detect(picUrl);
						// �������
						 AccessToken tokens=TokenService.getAccesstoken();
						if (detectResult.equals("") || detectResult == null) {

							
						
							 String jsonmessage=MakeServiceMessage.makeTextCustomMessage(op, "��������ͼƬ");
					        Boolean re= SendCusMessage.sendCustomSerMessage(tokens.getToken(), jsonmessage);
							respContent = "success";
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

							

						} else {
							 String jsonmessage=MakeServiceMessage.makeTextCustomMessage(op, detectResult);
						      Boolean re= SendCusMessage.sendCustomSerMessage(tokens.getToken(), jsonmessage);
							
							respContent = "success";
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

							

						}
						log.info("face ############### end");
					} else {
						log.info("message is repeats~~");
					}

				}
				// ����λ����Ϣ
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
					/**** �������λ����Ϣ ****/
					// ȡ���û��ľ�γ��
					String lng = requestMap.get("Location_Y");
					String lat = requestMap.get("Location_X");
					// ת��֮��γ��
					String bdLng = null;
					String bdLat = null;
					// ���ýӿ�ת������
					UserLocation userLocation = BaiduMapUtil.ConvertCoord(lng,
							lat);
					if (null != userLocation) {
						bdLng = userLocation.getBd09Lng();
						bdLat = userLocation.getBd09Lat();
					}
					userLocation.setOpenId(fromUserName);// ����� open id ���û���id
					userLocation.setLat(lat);
					userLocation.setLng(lng);

					// �����û�����λ��
					UserLocationDao lo = new UserLocationDaoJdbc();
					lo.saveLocation(userLocation);
					respContent = WeixinConstants.locationEndSay();
					respMessage = CreateMessage.CreateTextMessage(fromUserName,
							toUserName, respContent);
				}
				// ������Ϣ
				/*
				 * else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				 * // respContent = "�����͵���������Ϣ��"; }
				 */
				// ��Ƶ��Ϣ
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
					// respContent = "�����͵�����Ƶ��Ϣ��";
				}
			}
			// ���¼���������Ϣ
		} catch (Exception e) {
			log.info("��Ϣ�����쳣��xmlת��ʧ��");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
			// log.info("�������ݿ����"+sw.toString());
			log.warn("service����" + sw.toString());
			e.printStackTrace();

			respMessage = "success";
			e.printStackTrace();
		} finally {

			log.info("�������");
		}
		log.info("�ظ�����Ϣ��*******************" + respMessage);
		return respMessage;
	}

	/*
	 * public static void main(String[] args) throws
	 * UnsupportedEncodingException { String a="";
	 * System.out.println(a.getBytes("UTF-8").length); }
	 */
}
