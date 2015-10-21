package org.zr.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.message.zr.request.BaseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.model.dao.MessageDao;
import org.zr.model.dao.MessageDaoJdbc;

public class XiaoqUtil {
	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content
	 * @return
	 */
	private static Logger log=LoggerFactory.getLogger(XiaoqUtil.class);
	public static boolean isQqFace(String content) {
		boolean result = false;
		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param createTime 消息创建时间
	 * @return
	 */
	public static String formatTime(String createTime) {
		// 将微信传入的CreateTime转换成long类型，再乘以1000
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}
	//判断消息是否重复
	
	public static Boolean isRepeat(String mgid,String msgType,String fromUserName,String toUserName,Long createTime , Long gid){
		boolean result=false;
		MessageDao mdao = new MessageDaoJdbc(); // 如果不是一样的消息那么做请求处理
	//	mdao.selectByMid(mgid).size();
		
		if (mdao.selectByMid(mgid).size() == 0) {
			BaseMessage base = new BaseMessage();
			base.setCreateTime(createTime);
			base.setFromUserName(fromUserName);
			base.setToUserName(toUserName);
			base.setMsgType(msgType);
			base.setMsgId(gid);
			mdao.insertMessage(base);
			result=true;
		}else{
			log.warn("消息重复了～～");
		}
		return result;
	}
	
	
}
