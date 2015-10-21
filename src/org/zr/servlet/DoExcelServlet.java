package org.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.message.zr.request.TextMessage;
import org.zr.SignUtil.SignUtil;
import org.zr.model.dao.MessageDao;
import org.zr.model.dao.MessageDaoJdbc;
import org.zr.util.TimeUtil;
import org.zr.util.WeixinUtil;

public class DoExcelServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
        MessageDao dao=new MessageDaoJdbc();
        List<TextMessage> list=dao.selectText();
        Iterator<TextMessage> in=list.iterator();
        PrintWriter out = resp.getWriter();
        while(in.hasNext()){
        	TextMessage text=in.next();
        	  
            // ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��  
        	out.print("UserName---->"+text.getUsername());  
            out.print("Content---->"+text.getContent()); 
            out.println("Time---->"+TimeUtil.formatTime(String.valueOf(text.getCreateTime())));
        }
        
        
        
        
        out.close();  
        out = null;  
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//����excel
		
		
		super.doPost(req, resp);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
