package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.message.zr.response.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.model.dao.ArticleDao;
import org.zr.model.dao.ArticleDaoImpl;
import org.zr.model.dao.ArticleMapDao;
import org.zr.model.dao.ArticleMapDaoImpl;

public class MegMapServlet extends HttpServlet{
	private Logger log=LoggerFactory.getLogger(MegMapServlet.class);
	/***对前台提供map数据源*  增加 ，修改***/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		JSONObject jo=new JSONObject();
		
		JSONArray jarry=new JSONArray();
		PrintWriter pw=resp.getWriter();
		ArticleMapDao artmap=new ArticleMapDaoImpl();
		List<String> list=artmap.findAllMegMap();
		jo.put("total", list.size());
		Iterator< String > it=list.iterator();
		while (it.hasNext()) {
			String a=it.next();
			String[] b=a.split("@");
			JSONObject j=new JSONObject();
			j.put("id", b[2]);
			j.put("mid", b[0]);
			j.put("context", b[1]);
			jarry.add(j);
		}
		jo.put("rows", jarry);
		pw.print(jo);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		resp.setCharacterEncoding("utf-8");	
		PrintWriter pw=resp.getWriter();
		JSONObject js=new JSONObject();
	String action=req.getParameter("action");
	String mid=req.getParameter("mid");
	//String id=req.getParameter("id");
	String context=req.getParameter("context");
	log.info("context"+context);

	
	ArticleMapDao ardao=new ArticleMapDaoImpl();
	if(action.equals("add")){
		ardao.saveArticleMap(mid, context);
		js.put("msg", "success");
	}else if(action.equals("update")){
		String id=req.getParameter("id");
		ardao.UpdateByid(id, mid, context);
		js.put("msg", "success");
	}else{
		js.put("msg", "fail");
	}
		pw.print(js);
		pw.close();
	}

	}


