package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class LoginServlet extends HttpServlet{

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session=req.getSession(false);
		if (session == null) {
			session = req.getSession();
		}
		// ‰»Î’À∫≈∫Õ√‹¬Î
		String passwd=req.getParameter("passwd");
		String code=req.getParameter("code");
		PrintWriter pw=resp.getWriter();
		
		JSONObject js=new JSONObject();
		if(code.equals("85506069")&&passwd.equals("wsec!!8909")){
			session.setAttribute("login", "success");
			js.put("meg", "ok");
		
			pw.write(js.toString());
		}else{
			js.put("meg", "error");
			pw.write(js.toString());
		}
		
		pw.close();
	}

}
