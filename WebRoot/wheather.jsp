<%@page import="org.zr.entity.WeatherBean"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="org.zr.others.GetWheather"%>
<%@page import="org.zr.entity.WxLinkWs"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.util.*" import="org.zr.model.dao.*"
	import="org.zr.util.*" import="org.zr.service.*" %>
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
<title>天气预报页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta http-equiv="description" content="今日天气">

<meta name="viewport"content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0">


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
    
    
		<%
	
	String city=new String(request.getParameter("city").getBytes("iso-8859-1"),"utf-8");
	
	
	//JSONObject js=GetWheather.Wherther1(city);
	//String result=js.toString(); */
	
	%>
	<input type=hidden id="a1" value=<%=city%> />

 <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
 <script src="js/angular.min.js"></script>	
 <script src="js/zepto.min.js"></script>	
  <script src="js/wb.js"></script>




</head>

<body>

<div ng-app="weather" ng-controller="w1Controller">
<div class="title">今日{{wb.city}}天气</div>
<div class="title">今日{{wb.city}}pm2.5值{{wb.pm}}</div>

<div ng-repeat="data in index">
<p>{{data.title}}</p>
<p>{{data.zs}}</p>
<p>{{data.tipt}}</p>
<p>{{data.des}}</p>
</div>


<div ng-repeat="data in wlist">
<p>{{data.date}}</p>
<img src={{data.dayPictureUrl}}></img><img src={{data.nightPictureUrl}}></img>
<p>天气:{{data.weather}}</p>
<p>风力:{{data.wind}}</p>
<p>温度:{{data.temperature}}</p>
</div>
</div>




</body>

</html>
