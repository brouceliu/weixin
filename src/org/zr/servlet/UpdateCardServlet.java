package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.zr.card.UpdateCard;
import org.zr.entity.CardInfo;
import org.zr.service.card.CardService;
import org.zr.service.card.CardServiceImpl;

public class UpdateCardServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject jso=new JSONObject();
	
		PrintWriter ps=resp.getWriter();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// 需要传入 card type  描述修改 
		  String descript=req.getParameter("desc");
		  String id=req.getParameter("type");
		  String notice=req.getParameter("notice");
		 
         if(id.equals("")){
        	 jso.put("msg", "id is null");
        	 
		  }else{
			  CardService cservice=new CardServiceImpl();
			    CardInfo cs=cservice.getCardInformationByID(id);
				//String id=cs.getCardid();
			    
				String result=UpdateCard.update(notice, descript, cs);
				if(result.equals("ok")){
					
					jso.put("msg", "ok");
				}else{
				
					jso.put("msg", result);
				} 
		  }
		
		ps.print(jso.toString());
		ps.close();
	}
/**更新卡卷 servlet****/
	
	
	
}
