package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.others.GetWheather;
import org.zr.util.WuShangOrder;

import net.sf.json.JSONObject;

public class WeatherServlet extends HttpServlet{
	private static Logger log=LoggerFactory.getLogger(WeatherServlet.class);
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	// String city=new String(request.getParameter("city").getBytes("iso-8859-1"),"utf-8");
	request.setCharacterEncoding("UTF-8");  
    response.setCharacterEncoding("UTF-8");  
    request.getAttribute("city");
	String city=request.getParameter("city");
        log.info(city);
		JSONObject js=GetWheather.Wherther1(city);
		String result=js.toString();
		PrintWriter ps=response.getWriter();
		ps.print(js.toString());
		 log.info(result);
		ps.close();
	}

}
