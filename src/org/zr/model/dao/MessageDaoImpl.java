package org.zr.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.message.zr.request.BaseMessage;
import org.message.zr.request.TextMessage;
import org.springframework.jdbc.core.JdbcTemplate;

public class MessageDaoImpl implements MessageDao {
	private JdbcTemplate jd;

	@Override
	public String insertMessage(BaseMessage base) {
		//
		long time = base.getCreateTime();
		String duserid = base.getFromUserName();// 接收方
		String userid = base.getToUserName();// 发送方
		long msgid = base.getMsgId();
		String msgtype = base.getMsgType();
		String sql = "insert into message (msgid,time,duserid,userid,mtype) values (?,?,?,?,?)";
		Object[] params = new Object[] { msgid, time, duserid, userid, msgtype };
		jd.update(sql, params);
		return "yes";

	}
// 开发者id=tousername=duserid
	@Override
	public List<BaseMessage> selectByMid(String mid) {
		String sql = "select * from message where msgid = ?";
		List<BaseMessage> tolist = new ArrayList<BaseMessage>();
		Object[] params = new Object[] {mid};
		List lis = jd.queryForList(sql, params);
		Iterator<BaseMessage> it = lis.iterator();
		while (it.hasNext()) {
			BaseMessage bm = new BaseMessage();
			Map map = (Map) it.next();
			bm.setCreateTime((Long) map.get("time"));
			bm.setMsgId((Long) map.get("msgid"));
			bm.setToUserName((String) map.get("userid"));
			bm.setFromUserName((String) map.get("duserid"));
			bm.setMsgType((String) map.get("mtype"));
			tolist.add(bm);
		}
		return tolist;
	}

	@Override
	public String deleteMessage(String time) {
	
		return null;
	}

	public JdbcTemplate getJd() {
		return jd;
	}

	public void setJd(JdbcTemplate jd) {
		this.jd = jd;
	}
	@Override
	public String insertTextMessage(TextMessage text,String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TextMessage> selectText() {
		// 按照时间搜索
		
		return null;
		
	}
	@Override
	public List<TextMessage> selectTextByTime(String timeStart, String timeEnd) {
		// TODO Auto-generated method stub
		return null;
	}

}
