package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;





import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.zr.util.WeixinConstants;

public class ProtectServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//做登录校验；输入用户名 密码  验证码
		String uname=req.getParameter("name");
		String password =req.getParameter("pass");
		String code=req.getParameter("code");
		
	
		if(uname.equals(WeixinConstants.uname)&&password.equals(WeixinConstants.password)&&code.equals(WeixinConstants.ucode)){
			HttpSession s=req.getSession();
			s.setAttribute("ischeck", "ok");
			PrintWriter ps=resp.getWriter();
			JSONObject js=new JSONObject();
			js.put("meg", "ok");
			ps.print(js.toString());
			ps.close();
		/*	RequestDispatcher rd=req.getRequestDispatcher("http://119.79.233.21/Txwx/html/Down.jsp");
			rd.forward(req, resp);*/
			//resp.sendRedirect("Down.jsp");
		}else{
			PrintWriter ps=resp.getWriter();
			JSONObject js=new JSONObject();
			js.put("meg", "error");
			ps.print(js.toString());
			ps.close();
		}
		
		
	}

}
