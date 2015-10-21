package org.zr.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
/**过滤浏览器类 只有微信浏览器才可以访问资源***/
public class BrowserFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) arg0;
		String userAgent=req.getHeader("User-Agent");
		if(userAgent.contains("MicroMessenger")){
			arg2.doFilter(arg0, arg1);
		}else{
			HttpServletResponse resp=(HttpServletResponse) arg1;
			resp.setCharacterEncoding("gbk");
			PrintWriter out=resp.getWriter();
			out.write("请您用微信浏览器访问");
			out.close();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
