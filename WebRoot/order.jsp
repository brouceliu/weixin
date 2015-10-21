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
<title>用户绑定</title>
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

	<div class="zero">
		请绑定用户账号密码!

		<%
	//	String login="http://www.wushang.com/weixin/weiXinLogin.html#act=ID&psw=PASSWd&tt=TICKET";
		String openid = request.getParameter("openid");
		/* // String urls = "http://vote.wushang.com/WeixinTest/jump.jsx?htmlName=memberCenter/myHomeWeiXin"; */
		String urls=WeixinConstants.persioal;
		String sh1ticket = JsTicketUtil.sha1Ticket(urls);
		String tick = sh1ticket.split(",")[0];//加密参数
		String suiji = sh1ticket.split(",")[1];//随机字符
		String timestep = sh1ticket.split(",")[2];//时间错
		if (null != WxLinkWsuserImpl.findByOpenId(openid)) {
			WxLinkWs ws = WxLinkWsuserImpl.findByOpenId(openid);
		//	String login = WeixinConstants.TestUSerOrderUrl;
		String login=WeixinConstants.USerOrderUrl;
			String passwd = ws.getWspasswd();
			String wushangname = ws.getWsuser();
			login = login.replace("ID", wushangname)
					.replace("PASSWd", passwd).replace("TICKET", tick)
					.replace("NICK", timestep).replace("SUIJI", suiji);
			     response.sendRedirect(login);
		}
	%>

		<br> <span style="color:red;font-weight:bold "> <%
 	if (request.getAttribute("result") != null) {
 		out.println(request.getAttribute("result"));
 	}
 %>
		</span>
	</div>
	<div class="cet">
		<form id="wushang" action="/Txwx/LoginCheck" method="POST">
			<div class="one">
				<span class="title">输入账号</span> <span class="text"><input
					type="text" class="username" name="username" /></span>
			</div>
			<div class="two">
				<span class="title">输入密码</span> <span class="text"><input
					type="password" class="pwd" name="password" /></span>
			</div>
			<input type="hidden" name="openid" value=<%=openid%> />
			<div class="three">
				<input type="submit" value="点击绑定">
			</div>
		</form>
	</div>
</body>
<script>
	
</script>
</html>
