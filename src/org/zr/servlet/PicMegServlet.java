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
import org.zr.model.dao.ArticleDao;
import org.zr.model.dao.ArticleDaoImpl;

public class PicMegServlet extends HttpServlet{
	/****根据消息提供数据源***/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");	
	resp.setCharacterEncoding("utf-8");	
	JSONObject js=new JSONObject();//first
	JSONArray jarr=new JSONArray();
	ArticleDao articledao=new ArticleDaoImpl();
	List<Article> list=articledao.findAll();
	js.put("total", list.size());
	
	Iterator< Article> it=list.iterator();
	while(it.hasNext()){
		Article art=it.next();
		JSONObject jobject=new JSONObject();
		String [] a=art.getTitle().split("@");
		jobject.put("url", art.getUrl());
		jobject.put("title", a[0]);
		jobject.put("picurl", art.getPicUrl());
		jobject.put("desc", art.getDescription());
		jobject.put("mid", a[1]);
		jobject.put("id", a[2]);
		jarr.add(jobject);
	}
	js.put("rows", jarr);
	PrintWriter pw=resp.getWriter();
	pw.print(js);
	pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**增删微信的图文消息数据**/
		req.setCharacterEncoding("utf-8");	
		resp.setCharacterEncoding("utf-8");	
		PrintWriter pw=resp.getWriter();
		JSONObject js=new JSONObject();
	String action=req.getParameter("action");
	String mid=req.getParameter("mid");
	String title=req.getParameter("title");
	String desc=req.getParameter("description");
	String url=req.getParameter("url");
	String picurl=req.getParameter("picurl");
	Article article=new Article();
	article.setDescription(desc);
	article.setPicUrl(picurl);
	article.setUrl(url);
	article.setTitle(title);
	ArticleDao ardao=new ArticleDaoImpl();
	if(action.equals("add")){
		
		ardao.savePicMeg(article, mid);
		js.put("msg", "success");
	}else if(action.equals("update")){
		String id=req.getParameter("id");
		
		ardao.upDatePicMeg(id, article,mid);
		js.put("msg", "success");
	}else{
		js.put("msg", "fail");
	}
		pw.print(js);
		pw.close();
	}

}
