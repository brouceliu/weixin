package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.card;
import org.zr.model.dao.CardInfoDaoImpl;
import org.zr.model.dao.CardNumDao;
import org.zr.model.dao.CardNumDaoImol;
import org.zr.model.dao.CardTicketDaoImpl;

public class ChangeStatusServlet extends HttpServlet{
	private static Logger log = LoggerFactory.getLogger(ChangeStatusServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	String code=req.getParameter("code");
	String[] codecard=code.split("-p-");
	card ca=new card();
	ca.setCardcode(codecard[0]);
	ca.setCardpasswd(codecard[1]);
	ca.setCardstatus("yes");
	ca.setCardtype("CASH");
	CardNumDao cadao=new CardNumDaoImol();
	cadao.updateCard(ca);
	PrintWriter pw=resp.getWriter();
		JSONObject jso=new JSONObject();
		jso.put("message", "ok");
		pw.print(jso.toString());
		pw.close();
	}

}
