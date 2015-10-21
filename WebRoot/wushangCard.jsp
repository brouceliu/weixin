<%@page import="org.zr.entity.WxLinkWs"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.util.*" import="org.zr.model.dao.*"
	import="org.zr.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<title>跳转到卡卷接口</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="帐号绑定">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/demo.css">

</head>

<body>

	

		<%
	//	String login="http://www.wushang.com/weixin/weiXinLogin.html#act=ID&psw=PASSWd&tt=TICKET";
		String openid = request.getParameter("openid");
		//String urls = "http://vote.wushang.com/WeixinTest/jump.jsx?htmlName=memberCenter/myHomeWeiXin";
		String urls=WeixinConstants.WeixinGetCardUrl; //url填写要使用jssdk页面的url
		String sh1ticket = JsTicketUtil.sha1Ticket(urls);
		String tick = sh1ticket.split(",")[0];//加密参数
		String suiji = sh1ticket.split(",")[1];//随机字符
		String timestep = sh1ticket.split(",")[2];//时间错
		
		String all=CardTicketUtil.GetSignAndTicket("4","");
		String [] b=all.split(",");
		String cardsuiji=b[0];
		String cardtype=b[3];
		String cardtime=b[1];
		String cardid=b[2];
		String cardticket=b[4];
		String canum=b[5];
		String login = WeixinConstants.WeixinJuanUrl;
			
	   login = login.replace("TICKET", tick).replace("NICK", timestep).replace("SUIJI", suiji);
	   login = login.replace("CTT", cardticket).replace("CAID", cardid).replace("CARSJ", cardsuiji).replace("CARDTIME", cardtime).replace("CARDTYPE", cardtype).replace("NUMBER", canum);
	  // out.print(login);
	  response.sendRedirect(login);
		
	%>

	
	
	
</body>
<script>
	
</script>
</html>
