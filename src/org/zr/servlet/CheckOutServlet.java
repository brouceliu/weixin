package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.message.zr.request.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.download.CheckOutMessage;
import org.zr.model.dao.MessageDao;
import org.zr.model.dao.MessageDaoJdbc;
import org.zr.util.TimeUtil;

public class CheckOutServlet extends HttpServlet{
	private static Logger log=LoggerFactory.getLogger(CheckOutServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 用户提交 时间请求 按时间生成数据
		String starttime=req.getParameter("start");
		log.info(starttime);
		starttime=TimeUtil.toUnixTime(starttime);
		log.info(starttime);
		String endtime=req.getParameter("end");
		endtime=TimeUtil.toUnixTime(endtime);
		MessageDao mdao=new MessageDaoJdbc();
		PrintWriter out = resp.getWriter();
		JSONObject js=new JSONObject();
		List<TextMessage>  list= mdao.selectTextByTime(starttime, endtime);
		if(list==null || list.size()==0){
			js.put("meg", "nodata");
		}else{
		   CheckOutMessage.doexport("te", list);
	       js.put("meg", "ok");
		}
		out.print(js.toString());
	    out.close();  
		
	}

}
