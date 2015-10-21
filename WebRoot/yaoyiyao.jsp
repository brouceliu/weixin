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
<title>摇一摇领取卡劵</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta http-equiv="description" content="帐号绑定">

<meta name="viewport"content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<script src="js/zepto.min.js"></script>
<script src="js/card.js"></script>	
 <style type="text/css">
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
            text-align: left;
        }
    </style>
    
    


<script >
$(document).ready(function(){

	 var urls=document.location.href;
	    alert(urls);
	    var a=urls.split("?");
	    var b=a[1];
	    var c= b.split("=");
	    alert(c[0]);
	    alert(c[1]);
	    var d= c[1].replace("&state","");
	    var url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	    param = {appid:"wx6f1863a4b2802eea",secret:"d730b0bc988e97fb76ddeabf03a169e0",code:d,grant_type:"authorization_code"};
	    url=url.replace("APPID", "wx6f1863a4b2802eea");
	    url=url.replace("SECRET", "d730b0bc988e97fb76ddeabf03a169e0");
	    url=url.replace("CODE", d);
	    top.location=url;
});

</script>




</head>

<body>


<p class="title">测试摇一摇获取id</p>
<p class="name1">测试摇一摇获取id24:00前</p>



</body>

</html>
