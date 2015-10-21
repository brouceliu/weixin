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
 * 核心服务类 百度天气 ak F9af0fcc4b18993fc7a6559683068b31
 * 
 * 
 */
public class CoreService {
	private static Logger log = LoggerFactory.getLogger(CoreService.class);

	// 第三方用户唯一凭证
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = "";
		// AccessToken tokens=TokenUtil.getAccessToken();
		try {
			// 默认返回的文本消息内容
			String respContent = respMessage;
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);// 此处有异常
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				log.info("事件消息~~");
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = WeixinConstants.getMainMenu();
					respMessage = CreateMessage.CreateTextMessage(fromUserName,
							toUserName, respContent);

				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// O 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}

				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("11")) {
						// 天气预报
						respContent = "请您发送 城市+天气，如 武汉天气";
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					} else if (eventKey.equals("12")) {
						respContent = WeixinConstants.getMainMenu();
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					} else if (eventKey.equals("13")) {
						/**
						 * 我的订单项目 点击按钮 传入 id 查找用户名 密码 如果存在 做登录 查询用户订单列表
						 * 不存在则要求用户绑定武商网的帐号
						 ***/
						String result = WushangLogin.loginWushang(fromUserName);
						log.info("******用户登录结果是****" + result);
						if (result.equals("no")) {
							// 登录失败
							String mes = "您好！您没有绑定武商网账号" + "\n" + "请绑定武商网帐号";
							mes = mes
									+ "\n"
									+ "<a href='http://wx.wushang.com/Txwx/user.jsp"
									+ "?openid=" + fromUserName + "'"
									+ ">点我绑定</a>";
							respContent = mes;
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						} else {
							// 登录成功
							String mes = WuShangOrder
									.GetOrderInfo(fromUserName);
							log.info("login info is" + mes);
							respContent = mes
									+ "\n"
									+ "<a href='http://wx.wushang.com/Txwx/order.jsp"
									+ "?openid=" + fromUserName + "'"
									+ ">点我查看订单</a>";
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						}

					} else if (eventKey.equals("14")) {
						// 发送武商历史
						String mes = GetHistory.getwushang(toUserName,
								fromUserName);
						respMessage = mes;

					} else if (eventKey.equals("24")) {
						// 人脸识别按钮
						respContent = WeixinConstants.getUsage();
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					} else if (eventKey.equals("33")) {
						// 安卓app下载
						respContent = WeixinConstants.getUsage();
					} else if (eventKey.equals("37")) {

						String mes = "您好！欢迎使用会员功能。请点击下面文字进入会员中心" + "\n"
								+ "第一次请绑定武商网帐号";
						mes = mes + "\n"
								+ "<a href='http://wx.wushang.com/Txwx/user.jsp"
								+ "?openid=" + fromUserName + "'" + ">点我进入</a>";
						respContent = mes;
						respMessage = CreateMessage.CreateTextMessage(
								fromUserName, toUserName, respContent);

					}
				}
			} else {
				// 创建时间
				Long createTime = Long.parseLong(requestMap.get("CreateTime"));
				// 消息id
				String mgid = requestMap.get("MsgId");
				// if (mgid != null && !mgid.equals("")) {
				Long gid = Long.parseLong(mgid);
				log.info("messageid is " + gid);
				// }
				// 文本消息
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//	if (XiaoqUtil.isRepeat(mgid, msgType, fromUserName,toUserName, createTime, gid)) {
						// 文本消息内容
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
						// 判断用户发送的是否是单个QQ表情
						if (XiaoqUtil.isQqFace(content)) {
							// 用户发什么QQ表情，就返回什么QQ表情
							respContent = content;
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
							// 将文本消息对象转换成xml字符串
							// MessageUtil.textMessageToXml(textMessage);
						} else if (content.contains("天气")) {

							// 采用异步的方式给用户发送天气信息
							String[] ci = content.split("天气");
							log.info("查询计时开始");
							String msg = GetWheather.createWheatherMessage( fromUserName, toUserName,ci[0]);
						
							log.info("查询计时结束");
							// 给用户发送天气信息
							
							respMessage = msg;
						} else if (content.contains("绑定")) {

							String urls = WeixinConstants.persioal;
							String sh1ticket = JsTicketUtil.sha1Ticket(urls);
							String mes = "您好！欢迎使用会员功能。" + "\n"
									+ "第一次使用会员中心，请按照提示绑定武商网帐号";
							mes = mes
									+ "\n"
									+ "<a href='http://wx.wushang.com/Txwx/user.jsp"
									+ "?openid=" + fromUserName + "&ticket="
									+ sh1ticket + "'" + ">点我进入</a>";
							respContent = mes;
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						} else if (content.equals("1")) {
							// 给用户发送天气信息
							respContent = "请发送 城市+天气 如 武汉天气";
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						} 
						/*else if (content.contains("快递")) {
							// String company=content.replaceAll("快递",
							// "").trim();
							String[] comment = content.split("快递");
							// 处理快递单号
							respContent = KuaiDi.kD100(comment[0],
									comment[comment.length - 1]);
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						}*/ 
						
						else if (content.contains("客服")
								|| content.contains("kf")) {
							respMessage = CreateMessage.CreateCustomMessage(
									fromUserName, toUserName);
						}

						else if (content.equals("2")) {
							// 发送武商历史
							// String mes =
							// GetHistory.getwushang(toUserName,fromUserName);
							// 图文消息直接返回
							String mes = GetHistory.getwushang(toUserName,
									fromUserName);
							respMessage = mes;

						} else if (content.equals("3")) {
							// 发送人脸识别消息
							respContent = WeixinConstants.getUsage();
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);
						} else if (content.equals("7")) {

							// 返回地理位置消息提示
							respContent = WeixinConstants.locationSay();
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						} else if (content.equals("8")) {

							// 返回快递消息
							respContent = WeixinConstants.kdSay();
							respMessage = CreateMessage.CreateTextMessage(
									fromUserName, toUserName, respContent);

						} 
						else if (ReplyPicMeg.findPicMeg(content)) {

							// 多图文消息
							

							ReplyPicMeg re=new ReplyPicMeg();
							respMessage=	re.replayMeg(content, fromUserName,toUserName);
						} 
						else if (content.startsWith("附近")) {
							// 处理地理位置查询
							String keyword = content.replaceAll("附近", "")
									.trim();
							UserLocationDao userdao = new UserLocationDaoJdbc();
							UserLocation location = userdao
									.getLastlocation(fromUserName);
							if (null == location) {
								respContent = WeixinConstants.locationSay();
								respMessage = CreateMessage.CreateTextMessage(
										fromUserName, toUserName, respContent);
							} else {
								// 根据经纬度搜索
								List<BaiduPlace> placeList = BaiduMapUtil
										.searchPlace(keyword,
												location.getBd09Lat(),
												location.getBd09Lng());
								// 没有搜索到
								if (null == placeList || 0 == placeList.size()) {
									respContent = "不好意思，您超出服务区。";
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

				// 图片消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
					
			if (XiaoqUtil.isRepeat(mgid, msgType, fromUserName,
							toUserName, createTime, gid)) {
						log.info("face ############### start");
						/** isrepeat 有存储 **/
						String op = fromUserName;
						log.info("id is " + op);
						// 取得图片地址 异步发送人脸识别信息
						String picUrl = requestMap.get("PicUrl");
						String detectResult = FaceService.detect(picUrl);
						// 人脸检测
						 AccessToken tokens=TokenService.getAccesstoken();
						if (detectResult.equals("") || detectResult == null) {

							
						
							 String jsonmessage=MakeServiceMessage.makeTextCustomMessage(op, "请您换张图片");
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
				// 地理位置消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
					/**** 处理地理位置消息 ****/
					// 取得用户的经纬度
					String lng = requestMap.get("Location_Y");
					String lat = requestMap.get("Location_X");
					// 转化之后经纬度
					String bdLng = null;
					String bdLat = null;
					// 调用接口转化坐标
					UserLocation userLocation = BaiduMapUtil.ConvertCoord(lng,
							lat);
					if (null != userLocation) {
						bdLng = userLocation.getBd09Lng();
						bdLat = userLocation.getBd09Lat();
					}
					userLocation.setOpenId(fromUserName);// 这里的 open id 是用户的id
					userLocation.setLat(lat);
					userLocation.setLng(lng);

					// 保存用户地理位置
					UserLocationDao lo = new UserLocationDaoJdbc();
					lo.saveLocation(userLocation);
					respContent = WeixinConstants.locationEndSay();
					respMessage = CreateMessage.CreateTextMessage(fromUserName,
							toUserName, respContent);
				}
				// 链接消息
				/*
				 * else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				 * // respContent = "您发送的是链接消息！"; }
				 */
				// 音频消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
					// respContent = "您发送的是音频消息！";
				}
			}
			// 非事件推送类消息
		} catch (Exception e) {
			log.info("消息处理异常，xml转化失败");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
			// log.info("插入数据库错误"+sw.toString());
			log.warn("service错误" + sw.toString());
			e.printStackTrace();

			respMessage = "success";
			e.printStackTrace();
		} finally {

			log.info("处理结束");
		}
		log.info("回复的消息是*******************" + respMessage);
		return respMessage;
	}

	/*
	 * public static void main(String[] args) throws
	 * UnsupportedEncodingException { String a="";
	 * System.out.println(a.getBytes("UTF-8").length); }
	 */
}
