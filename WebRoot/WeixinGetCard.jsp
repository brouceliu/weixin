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
<title>领取卡劵页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta http-equiv="description" content="帐号绑定">

<meta name="viewport"content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0">


 <style type="text/css">
 body{
  background:#C6E2FF;
 }
        .b2 {
            background:white;
            width: 100%;
            float: left;
            position: relative;
            height: 29px;
            padding: 10px 10px 10px 10px;
        }

        .title{
            text-align: center;
        }
        .name1{
            text-align: center;
        }
         .name2{
            text-align: center;
        }
    </style>
    
    
		<%	
		String urls=WeixinConstants.WeixinCardMy; //url填写要使用jssdk页面的url
		String sh1ticket = JsTicketUtil.sha1Ticket(urls);
		String tick = sh1ticket.split(",")[0];//加密参数
		String suiji = sh1ticket.split(",")[1];//随机字符
		String timestep = sh1ticket.split(",")[2];//时间错
		
		String all=CardTicketUtil.GetSignAndTicket("14" ,"pwRFtt8NZCdj5Muw8UkJqwkmaHnY");//传入 cardinfo id  cardid
		String [] b=all.split(",");
		String cardsuiji=b[0];
		String cardtype=b[3];
		String cardtime=b[1];
		String cardid=b[2];
		String cardticket=b[4];
		String canum=b[5];	
	%>
<script src="js/wushang.js"></script>
 <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
 <script src="js/zepto.min.js"></script>	

<script src="js/card.js"></script>	


</head>

<body >
<input type=hidden id="a1" value=<%=sh1ticket%> />
<input type=hidden id="a2" value=<%=tick%> />
<input type=hidden id="a3" value=<%=suiji%> />
<input type=hidden id="a4" value=<%=timestep%> />
<input type=hidden id="a5" value=<%=cardsuiji%> />
<input type=hidden id="a6" value=<%=cardtype%> />
<input type=hidden id="a7" value=<%=cardtime%> />	
<input type=hidden id="a8" value=<%=cardid%> />	
<input type=hidden id="a9" value=<%=cardticket%> />	
<input type=hidden id="a0" value=<%=canum%> />	
<p class="title">“荆楚网”领取卡劵页面</p>
<p class="name1">抢完即止，可以转赠</p>
<p class="name2">每人限领一个</p>

<!-- <input class="b2" type="button" id="addcard" value="领取卡劵" /> -->
</body>

</html>
