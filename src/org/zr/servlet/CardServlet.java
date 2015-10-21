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
import org.zr.card.CreateCard;

public class CardServlet extends HttpServlet{
	private Logger log=LoggerFactory.getLogger(CardServlet.class);
/****处理优惠卷的信息    
 * 1 折扣卷
 * 2 代金卷
 * 3 礼品卷
 * 4 团购卷
 * 5 优惠卷
 * ***/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String namefu=req.getParameter("namefu");
		String url=req.getParameter("url");//logo
		String color=req.getParameter("color");
		String cardtype=req.getParameter("cardtype");//卡类型
		String name=req.getParameter("name");//名字
		String notice=req.getParameter("notice");//提醒
		String description=req.getParameter("description");
		String sku=req.getParameter("sku");
		String start=req.getParameter("datastart");
		String end=req.getParameter("dataend");
		
		String c1=req.getParameter("cusname01");
		String curl1=req.getParameter("cusurl01");
		String c2=req.getParameter("cusname02");
		String curl2=req.getParameter("cusurl02");
		JSONObject js=new JSONObject();
		if(cardtype.equals("CASH")){
			String qiyong=req.getParameter("qiyong");
			String jianmian=req.getParameter("jianmian");
			String a=CreateCard.CreateCashCard(url, qiyong, jianmian, name, color, notice, description, sku, start, end, namefu,c1,curl1,c2,curl2);
			js.put("mes", "创建成功id是>>>>>"+a);
		}else if(cardtype.equals("DISCOUNT")){
			String zhe=req.getParameter("zhe");//打几折
			String a=CreateCard.CreateDiscountCard(url, zhe, name, color, notice, description, sku, start, end);//创建折扣卷
			js.put("mes", "id is>>>>>"+a);
		}
		
		PrintWriter ps=resp.getWriter();
		
		
		ps.print(js.toString());
		ps.close();
		
	}

}
